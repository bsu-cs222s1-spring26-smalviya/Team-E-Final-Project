package Model;

import java.time.LocalDateTime;

public class Transaction {
    private double amount;
    private String category;
    private LocalDateTime date;
    private String description;

    public Transaction(double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.date = LocalDateTime.now();
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date.toString();
    }

}
