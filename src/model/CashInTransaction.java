package model;

public class CashInTransaction extends Transaction {
    public CashInTransaction(double amount) {
        super("CASH-IN", amount, "Funds added to account");
    }

    @Override
    public String getSummary() {
        return getFormattedDateTime() + " | " + getType()
                + " | PHP " + String.format("%.2f", getAmount())
                + " | " + getDetails();
    }
}
