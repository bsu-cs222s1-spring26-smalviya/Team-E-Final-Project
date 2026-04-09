package Controller;

import Graphing.GraphGenerator;
import Graphing.MoneyGoalVisualizer;
import Model.Account;
import Model.MoneyGoal;
import Model.Transaction;
import View.ShowPNG;
import View.User;
import Model.DataStorage;
import Converter.CurrencyConverter;
import Converter.ExchangeRateParser;
import Converter.ConverterException;

import java.io.IOException;
import java.util.List;

public class FinanceManager {
    private Account currentAccount;
    final private User ui;

    public FinanceManager() {
        this.ui = new User();
    }

    public void start() {
        boolean isRunning = true;

        String username = ui.getUserInputString("Welcome!\nPlease enter your username: ");

        boolean hasSaveData = DataStorage.checkLocalData(username);

        if (hasSaveData) {
            ui.displayMessage("Loading your saved data...");
            try {
                this.currentAccount = DataStorage.loadAccount(username);
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
                    generateGraph();
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

        double amount;
        while (true) {
            try {
                amount = ui.getUserInputDouble("Enter the amount to convert:");
                break;
            } catch (Exception e) {
                ui.displayMessage("Invalid input! Please try again.");
            }
        }

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
            DataStorage.saveAccount(currentAccount);
            ui.displayMessage("Data successfully saved.");
        } catch (IOException e) {
            ui.displayMessage("Failed to save data. Error: " + e.getMessage());
        }
    }

    private void createNewGoal() {
        String goalName = ui.getUserInputString("Please enter your goal name: ");

        double targetAmount;
        while (true) {
            try {
                targetAmount = ui.getUserInputDouble("Please enter your target amount: ");
                break;
            } catch (Exception e) {
                ui.displayMessage("Invalid input! Please try again.");
            }
        }

        String deadline = ui.getUserInputString("Please enter your deadline: ");
        MoneyGoal moneyGoal = new MoneyGoal(goalName, targetAmount, deadline);
        currentAccount.addMoneyGoals(moneyGoal);
    }

    private void handleTransaction() {
        boolean amountIsNotSufficient = false;

        double amount;
        while (true) {
            try {
                amount = ui.getUserInputDouble("Please enter the transaction amount. Use the prefix \"+\" for deposits and \"-\" for withdrawals.\n:");
                break;
            } catch (Exception e) {
                ui.displayMessage("Invalid input! Please try again.");
            }
        }

        if (amount + currentAccount.getBalance() < 0) {
            amountIsNotSufficient = true;
        }
        while (amountIsNotSufficient) {

            while (true) {
                try {
                    amount = ui.getUserInputDouble("Insufficient balance, operation failed, please try again.Your balance is:" + currentAccount.getBalance() + "\n:");
                    break;
                } catch (Exception e) {
                    ui.displayMessage("Invalid input! Please try again.");
                }
            }
            if (amount + currentAccount.getBalance() >= 0) {
                amountIsNotSufficient = false;
            }
        }

        String description = ui.getUserInputString("Please describe this transaction.\n:");

        currentAccount.addTransaction(new Transaction(amount, description));
        saveData(currentAccount);
    }

    public String addTransaction(double amount, String description) {
        if (amount + currentAccount.getBalance() < 0) {
            return ("Insufficient funds!");
        }

        currentAccount.addTransaction(new Transaction(amount, description));
        saveData(currentAccount);

        return ("Transaction added. New balance: " + currentAccount.getBalance());
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void initAccount(String username) {
        boolean hasSaveData = DataStorage.checkLocalData(username);

        if (hasSaveData) {
            try {
                currentAccount = DataStorage.loadAccount(username);
            } catch (IOException e) {
                System.out.println("Error loading account.");
            }
        } else {
            currentAccount = new Account(username, 0.0);
            saveData(currentAccount);
        }
    }

    private void generateGraph() {
        String fileName = "data/" + currentAccount.getUsername() + "_data.json ";
        try {
            GraphGenerator.generateCharts(fileName);
        } catch (Exception e) {
            ui.displayMessage("Error: " + e.getMessage());
        }
        try {
            Account account = DataStorage.readUser(fileName);
            List<MoneyGoal> moneyGoals = account.getMoneyGoalList();
            MoneyGoalVisualizer moneyGoalVisualizer = new MoneyGoalVisualizer();
            StringBuilder goalBars = new StringBuilder();
            for (MoneyGoal m : moneyGoals) {
                goalBars.append(moneyGoalVisualizer.generateGoalBar(account, m));
            }
            ui.displayMoneyGoalVisualizer(goalBars.toString());

        } catch (Exception e) {
            ui.displayMessage("Error: " + e.getMessage());
        }

        ShowPNG showPNG = new ShowPNG();

        try {
            showPNG.showPng("data/" + currentAccount.getUsername() + "-income_chart.png");
        } catch (Exception e) {
            ui.displayMessage("Error: " + e.getMessage());
        }

        try {
            showPNG.showPng("data/" + currentAccount.getUsername() + "-expense_chart.png");
        } catch (Exception e) {
            ui.displayMessage("Error: " + e.getMessage());
        }
    }

    //public static void main(String[] args) {
        //FinanceManager app = new FinanceManager();
        //app.start();
    //}
}
