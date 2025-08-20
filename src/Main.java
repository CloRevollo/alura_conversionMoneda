import API.ExchangeRateHttpClient;
import API.ExchangeRateHttpRequest;
import API.ExchangeRateHttpResponse;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("Error: No se encontró la API_KEY en las variables de entorno");
            return;
        }

        ExchangeRateHttpClient apiClient = new ExchangeRateHttpClient(apiKey);

        while (true) {
            mostrarMenu();
            System.out.print("Ingrese su opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido");
                scanner.next();
                continue;
            }

            int opcion = scanner.nextInt();

            if (opcion == 7) {
                System.out.println("¡Muchas gracias por usar nuestro sistema!");
                break;
            }

            if (opcion < 1 || opcion > 7) {
                System.out.println("Opción inválida. Por favor seleccione una opción del 1 al 7.");
                continue;
            }

            System.out.print("Ingrese el valor a convertir: ");

            if (!scanner.hasNextDouble()) {
                System.out.println("Por favor ingrese un valor numérico válido");
                scanner.next();
                continue;
            }

            double valor = scanner.nextDouble();

            if (valor <= 0) {
                System.out.println("El valor debe ser mayor a 0");
                continue;
            }

            procesarConversion(opcion, valor, apiClient);
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n====================");
        System.out.println("Bienvenid@ al sistema");
        System.out.println("de conversión de monedas");
        System.out.println("====================");
        System.out.println("Menu de opciones: ");
        System.out.println("1. Dólar (USD) -> Peso argentino (ARS)");
        System.out.println("2. Dólar (USD) -> Peso chileno (CLP)");
        System.out.println("3. Dólar (USD) -> Peso colombiano (COP)");
        System.out.println("4. Dólar (USD) -> Euro (EUR)");
        System.out.println("5. Peso chileno (CLP) -> Dólar (USD)");
        System.out.println("6. Peso chileno (CLP) -> Euro (EUR)");
        System.out.println("7. Salir");
    }


    private static void procesarConversion(int opcion, double valor, ExchangeRateHttpClient apiClient) {
        ExchangeRateHttpRequest request;
        ExchangeRateHttpResponse response;

        switch (opcion) {
            case 1:
                request = new ExchangeRateHttpRequest("USD", "ARS", valor);
                response = apiClient.convertCurrency(request);
                mostrarResultado(response, "Dólares", "Pesos Argentinos");
                break;
            case 2:
                request = new ExchangeRateHttpRequest("USD", "CLP", valor);
                response = apiClient.convertCurrency(request);
                mostrarResultado(response, "Dólares", "Pesos Chilenos");
                break;
            case 3:
                request = new ExchangeRateHttpRequest("USD", "COP", valor);
                response = apiClient.convertCurrency(request);
                mostrarResultado(response, "Dólares", "Pesos Colombianos");
                break;
            case 4:
                request = new ExchangeRateHttpRequest("USD", "EUR", valor);
                response = apiClient.convertCurrency(request);
                mostrarResultado(response, "Dólares", "Euros");
                break;
            case 5:
                request = new ExchangeRateHttpRequest("CLP", "USD", valor);
                response = apiClient.convertCurrency(request);
                mostrarResultado(response, "Pesos Chilenos", "Dólares");
                break;
            case 6:
                request = new ExchangeRateHttpRequest("CLP", "EUR", valor);
                response = apiClient.convertCurrency(request);
                mostrarResultado(response, "Pesos Chilenos", "Euros");
                break;
            default:
                System.out.println("Opción no válida");
        }


        private static void mostrarResultado(ExchangeRateHttpResponse response, String fromCurrency, String toCurrency) {
        if (response.isSuccessful()) {
            System.out.println("\n--- Resultado de la conversión ---");
            System.out.printf("%.2f %s = %.2f %s%n",
                    response.getOriginalAmount(), fromCurrency,
                    response.getConvertedAmount(), toCurrency);
            System.out.printf("Tasa de cambio: 1 %s = %.4f %s%n",
                    response.getFromCurrency(), response.getExchangeRate(), response.getToCurrency());
        } else {
            System.out.println("Error en la conversión: " + response.getErrorMessage());
        }
    }
}