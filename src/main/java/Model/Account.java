package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    final private String username;
    private double balance;
    final private List<Transaction> transactionHistory = new ArrayList<>();
    final private List<MoneyGoal> moneyGoals = new ArrayList<>();

    public Account(String username, double initialBalance) {
        this.username = username;
        this.balance = initialBalance;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        StringBuilder transactionHistory = new StringBuilder();
        for (Transaction transaction : this.transactionHistory) {
            transactionHistory.append(transaction);
            transactionHistory.append("\n");
        }
        return transactionHistory.toString();
    }

    public List<Transaction> getTransactionList() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public String getMoneyGoals() {
        StringBuilder moneyGoals = new StringBuilder();
        for (MoneyGoal moneyGoal : this.moneyGoals) {
            moneyGoals.append(moneyGoal);
            moneyGoals.append("\n");
        }
        return moneyGoals.toString();
    }

    public List<MoneyGoal> getMoneyGoalList() {
        return Collections.unmodifiableList(moneyGoals);
    }

    public boolean hasMoneyGoals() {
        return !moneyGoals.isEmpty();
    }

    public void addMoneyGoals(MoneyGoal moneyGoal) {
        this.moneyGoals.add(moneyGoal);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
        this.balance += transaction.getAmount();
    }
}
