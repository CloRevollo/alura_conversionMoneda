package API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ExchangeRateHttpClient {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;
    private final HttpClient httpClient;

    public ExchangeRateHttpClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public ExchangeRateHttpResponse convertCurrency(ExchangeRateHttpRequest request) {
        try {
            double exchangeRate = getExchangeRate(request.getFromCurrency(), request.getToCurrency());

            if (exchangeRate == -1) {
                return new ExchangeRateHttpResponse("No se pudo obtener la tasa de cambio");
            }

            double convertedAmount = request.getAmount() * exchangeRate;

            return new ExchangeRateHttpResponse(
                    request.getFromCurrency(),
                    request.getToCurrency(),
                    request.getAmount(),
                    convertedAmount,
                    exchangeRate
            );

        } catch (Exception e) {
            return new ExchangeRateHttpResponse("Error inesperado: " + e.getMessage());
        }
    }

    private double getExchangeRate(String fromCurrency, String toCurrency) {
        String url = BASE_URL + apiKey + "/latest/" + fromCurrency;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error HTTP: " + response.statusCode());
                return -1;
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            // Verificar si la respuesta fue exitosa
            if (!json.get("result").getAsString().equals("success")) {
                System.out.println("Error en la API: " + json.get("error-type").getAsString());
                return -1;
            }

            JsonObject rates = json.getAsJsonObject("conversion_rates");

            if (!rates.has(toCurrency)) {
                System.out.println("Moneda no soportada: " + toCurrency);
                return -1;
            }

            return rates.get(toCurrency).getAsDouble();

        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return -1;
        } catch (InterruptedException e) {
            System.out.println("Conexión interrumpida: " + e.getMessage());
            Thread.currentThread().interrupt();
            return -1;
        } catch (JsonSyntaxException e) {
            System.out.println("Error al procesar la respuesta JSON: " + e.getMessage());
            return -1;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return -1;
        }
    }
}