package model;

public class TransferTransaction extends Transaction {
    private final String otherPartyMobile;

    public TransferTransaction(double amount, String otherPartyMobile, boolean sent) {
        super(sent ? "TRANSFER SENT" : "TRANSFER RECEIVED", amount,
                (sent ? "Sent to " : "Received from ") + otherPartyMobile);
        this.otherPartyMobile = otherPartyMobile;
    }

    public String getOtherPartyMobile() { return otherPartyMobile; }

    @Override
    public String getSummary() {
        return getFormattedDateTime() + " | " + getType()
                + " | PHP " + String.format("%.2f", getAmount())
                + " | " + getDetails();
    }
}
