package API;

public class ExchangeRateHttpRequest {
    private final String fromCurrency;
    private final String toCurrency;
    private final double amount;

    public ExchangeRateHttpRequest(String fromCurrency, String toCurrency, double amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("ExchangeRateHttpRequest{fromCurrency='%s', toCurrency='%s', amount=%.2f}",
                fromCurrency, toCurrency, amount);
    }
}