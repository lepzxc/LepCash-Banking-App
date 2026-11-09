package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    private final String type;
    private final double amount;
    private final String details;
    private final LocalDateTime dateTime;

    protected Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.details = details;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDetails() { return details; }
    public LocalDateTime getDateTime() { return dateTime; }

    public String getFormattedDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public abstract String getSummary();
}
