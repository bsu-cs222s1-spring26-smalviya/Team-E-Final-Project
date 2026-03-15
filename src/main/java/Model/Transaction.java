package Model;

import java.time.LocalDateTime;

public class Transaction {
    private double amount;
    private String category;
    private String date;
    private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;

        if (amount < 0) {
            this.category = "Withdrawal";
        } else {
            this.category = "Deposit";
        }
        this.date = LocalDateTime.now().toString();
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
        return date;
    }

}
