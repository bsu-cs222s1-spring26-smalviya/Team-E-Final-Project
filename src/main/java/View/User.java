package View;

import Model.Account;
import Model.MoneyGoal;
import Model.Transaction;

import java.util.List;
import java.util.Scanner;

public class User {
    private Scanner scanner;

    public User() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println();
        System.out.println("--- Finance Manager ---");
        System.out.println("1. Record a Transaction");
        System.out.println("2. View Transaction History");
        System.out.println("3. View Financial Charts");
        System.out.println("4. Currency Converter");
        System.out.println("5. Manage Money Goals");
        System.out.println("6. Exit");
        System.out.println();
    }

    public String getUserInputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public double getUserInputDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayTransactionHistory(Account account) {
        List<Transaction> transactionHistory = account.getTransactionHistory();
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }

    }

    public void displayMoneyGoal(Account account) {
        List<MoneyGoal> moneyGoals = account.getMoneyGoals();
        for (MoneyGoal moneyGoal : moneyGoals) {
            System.out.println(moneyGoal);
            System.out.printf("Completion Percentage Of Goal: %.2f%%\n",moneyGoal.calculateCompletion(account.getBalance()));
        }
    }
}
