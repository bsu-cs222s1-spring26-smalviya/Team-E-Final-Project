package Model;

import java.util.ArrayList;

public class Account {
    private String username;
    private double balance;
    private ArrayList<Transaction> transactionHistory;
    private ArrayList<MoneyGoal> moneyGoals;

    public Account(String username, double initialBalance) {
        this.username = username;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.moneyGoals = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public ArrayList<MoneyGoal> getMoneyGoals() {
        return moneyGoals;
    }

    public void addMoneyGoals(MoneyGoal moneyGoal) {
        this.moneyGoals.add(moneyGoal);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
        this.balance += transaction.getAmount();
    }
}
