package Model;

import java.time.LocalDateTime;

public class Transaction {
    final private double amount;
    final private String type;
    final private String date;
    final private String category;
    final private String description;

    public Transaction(double amount, String category, String description) {
        this.amount = amount;

        if (amount < 0) {
            this.type = "Withdrawal";
        } else {
            this.type = "Deposit";
        }
        this.date = LocalDateTime.now().toString();
        this.category = category;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
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
        return "Amount: " + amount + "\n" + "Type: " + type + "\n" + "Date: " + date + "\n" + "Category: " + category + "\n" + "Description: " + description + "\n";
    }

}
