package Controller;

import Model.Account;
import Model.MoneyGoal;
import Model.Transaction;
import View.User;
import Model.DataStorage;
import Converter.CurrencyConverter;
import Converter.ExchangeRateParser;
import Converter.ConverterException;

import java.io.IOException;

public class FinanceManager {
    private Account currentAccount;
    final private User ui;
    final private DataStorage ds;

    public FinanceManager() {
        this.ui = new User();
        this.ds = new DataStorage();
    }

    public void start() {
        boolean isRunning = true;

        String username = ui.getUserInputString("Welcome!\nPlease enter your username: ");

        boolean hasSaveData = ds.checkLocalData(username);

        if (hasSaveData) {
            ui.displayMessage("Loading your saved data...");
            try {
                this.currentAccount = ds.loadAccount(username);
                ui.displayMessage("Account loaded successfully!");
            } catch (IOException e) {
                ui.displayMessage("Failed to load data. Error: " + e.getMessage());
            }

        } else {
            ui.displayMessage("You are a new customer!");
            ui.displayMessage("Creating a new account for you...");

            this.currentAccount = new Account(username, 0.0);
            saveData(currentAccount);
            ui.displayMessage("Account created!");
        }

        ui.displayMessage("Welcome to the Finance Manager, " + currentAccount.getUsername() + "!");
        while (isRunning) {
            ui.displayMainMenu();
            String choice = ui.getUserInputString("Please select an option: ");

            switch (choice) {
                case "1":
                    handleTransaction();
                    break;
                case "2":
                    ui.displayTransactionHistory(currentAccount);
                    break;
                case "3":
                    ui.displayMessage("(NOT YET IMPLEMENTED)");
                    break;
                case "4":
                    handleCurrencyConverter();
                    break;
                case "5":
                    moneyGoal();
                    break;
                case "6":
                    ui.displayMessage("Thank you for your use.\nGoodbye!");
                    isRunning = false;
                    saveData(currentAccount);
                    break;
                default:
                    ui.displayMessage("Please enter a number from 1 to 5!");
            }
        }
    }

    private void moneyGoal() {
        if (!currentAccount.hasMoneyGoals()) {
            ui.displayMessage("You do not have a goal yet, please create one.");
            createNewGoal();
        }
        ui.displayMoneyGoal(currentAccount);

        boolean wantAddMoneyGoals = true;
        while (wantAddMoneyGoals) {
            if (ui.getUserInputString("Do you want to add money goals? (y/n): ").equalsIgnoreCase("y")) {
                createNewGoal();
                ui.displayMoneyGoal(currentAccount);
            } else {
                wantAddMoneyGoals = false;
            }
        }
        saveData(currentAccount);
    }

    private void handleCurrencyConverter() {
        ui.displayMessage("\n--- Live Currency Converter ---");

        String fromCurrency = ui.getUserInputString("Enter base currency (e.g., USD, EUR, CNY):").toUpperCase();
        String toCurrency = ui.getUserInputString("Enter target currency (e.g., USD, EUR, CNY): ").toUpperCase();

        double amount = ui.getUserInputDouble("Enter the amount to convert:");

        ui.displayMessage("Fetching live exchange rates from server...");

        try {
            CurrencyConverter currencyConverter = new CurrencyConverter();
            String jsonResult = currencyConverter.getExchangeRateJson(fromCurrency, toCurrency);

            ExchangeRateParser exchangeRateParser = new ExchangeRateParser();
            double rate = exchangeRateParser.parseExchangeRateJson(jsonResult, toCurrency);

            double convertedAmount = amount * rate;
            ui.displayMessage(String.format("Success! %.2f %s = %.2f %s (Rate: %.4f)", amount, fromCurrency, convertedAmount, toCurrency, rate));

        } catch (ConverterException e) {
            ui.displayMessage("Conversion failed: " + e.getMessage());
        }
    }

    private void saveData(Account currentAccount) {
        try {
            ds.saveAccount(currentAccount);
            ui.displayMessage("Data successfully saved.");
        } catch (IOException e) {
            ui.displayMessage("Failed to save data. Error: " + e.getMessage());
        }
    }

    private void createNewGoal() {
        String goalName = ui.getUserInputString("Please enter your goal name: ");
        double targetAmount = ui.getUserInputDouble("Please enter your target amount: ");
        String deadline = ui.getUserInputString("Please enter your deadline: ");
        MoneyGoal moneyGoal = new MoneyGoal(goalName, targetAmount, deadline);
        currentAccount.addMoneyGoals(moneyGoal);
    }

    private void handleTransaction() {
        boolean amountIsNotSufficient = false;
        double amount = ui.getUserInputDouble("Please enter the transaction amount. Use the prefix \"+\" for deposits and \"-\" for withdrawals.\n:");
        if (amount + currentAccount.getBalance() < 0) {
            amountIsNotSufficient = true;
        }
        while (amountIsNotSufficient) {
            amount = ui.getUserInputDouble("Insufficient balance, operation failed, please try again.Your balance is:" + currentAccount.getBalance() + "\n:");
            if (amount + currentAccount.getBalance() >= 0) {
                amountIsNotSufficient = false;
            }
        }

        String description = ui.getUserInputString("Please describe this transaction.\n:");

        currentAccount.addTransaction(new Transaction(amount, description));
        saveData(currentAccount);
    }

    public static void main(String[] args) {
        FinanceManager app = new FinanceManager();
        app.start();
    }
}
