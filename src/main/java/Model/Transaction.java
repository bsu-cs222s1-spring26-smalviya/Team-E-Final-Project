package Model;

import java.time.LocalDateTime;

public class Transaction {
    final private double amount;
    final private String transactionType;
    final private String category;
    final private String date;
    final private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;

        if (amount < 0) {
            this.transactionType = "Withdrawal";
        } else {
            this.transactionType = "Deposit";
        }
        this.category = "Unspecified";
        this.date = LocalDateTime.now().toString();
        this.description = description;
    }

    public Transaction(double amount, String category, String description) {
        this.amount = amount;

        if (amount < 0) {
            this.transactionType = "Withdrawal";
        } else {
            this.transactionType = "Deposit";
        }
        this.category = category;
        this.date = LocalDateTime.now().toString();
        this.description = description;
    }



    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
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

    public String toString() {
        return "Amount: " + amount + "\n" + "Type: " + transactionType + "\n" + "Category: " + category + "\n" + "Date: " + date + "\n" + "Description: " + description + "\n";
    }

}
