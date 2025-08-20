package API;

public class ExchangeRateHttpResponse {
    private final boolean successful;
    private final String fromCurrency;
    private final String toCurrency;
    private final double originalAmount;
    private final double convertedAmount;
    private final double exchangeRate;
    private final String errorMessage;

    // Constructor para respuesta exitosa
    public ExchangeRateHttpResponse(String fromCurrency, String toCurrency,
                                    double originalAmount, double convertedAmount, double exchangeRate) {
        this.successful = true;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.exchangeRate = exchangeRate;
        this.errorMessage = null;
    }

    // Constructor para respuesta con error
    public ExchangeRateHttpResponse(String errorMessage) {
        this.successful = false;
        this.fromCurrency = null;
        this.toCurrency = null;
        this.originalAmount = 0;
        this.convertedAmount = 0;
        this.exchangeRate = 0;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        if (successful) {
            return String.format("ExchangeRateHttpResponse{successful=true, %.2f %s = %.2f %s, rate=%.4f}",
                    originalAmount, fromCurrency, convertedAmount, toCurrency, exchangeRate);
        } else {
            return String.format("ExchangeRateHttpResponse{successful=false, error='%s'}", errorMessage);
        }
    }
}
