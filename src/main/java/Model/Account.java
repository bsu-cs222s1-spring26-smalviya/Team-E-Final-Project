package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private String username;
    private double balance;
    private List<Transaction> transactionHistory = new ArrayList<>();
    private List<MoneyGoal> moneyGoals = new ArrayList<>();

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

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public List<MoneyGoal> getMoneyGoals() {
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
