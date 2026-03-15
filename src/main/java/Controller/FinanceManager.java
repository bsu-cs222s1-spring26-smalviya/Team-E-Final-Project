package Controller;

import Model.Account;
import Model.Transaction;
import View.User;
import Model.DataStorage;
import Converter.CurrencyConverter;
import Converter.ExchangeRateParser;
import Converter.ConverterException;

public class FinanceManager {
    private Account currentAccount;
    private User ui;
    private DataStorage ds;

    public FinanceManager() {
        this.ui = new User();
        this.ds = new DataStorage();
    }

    public void start() {
        boolean isRuning = true;

        String username = ui.getUserInputString("Welcome!\nPlease enter your username: ");

        boolean hasSaveData = ds.checkLocalData(username);

        if (hasSaveData) {
            ui.displayMessage("Loading your saved data...");
            this.currentAccount = ds.loadAccount(username);

        } else {
            ui.displayMessage("You are a new customer!");
            ui.displayMessage("Creating a new account for you...");

            this.currentAccount = new Account(username, 0.0);
            ds.saveAccount(this.currentAccount);
            ui.displayMessage("Account created!");
        }

        ui.displayMessage("Welcome to the Finance Manager, " + currentAccount.getUsername() + "!");
        while (isRuning) {
            ui.displayMainMenu();
            String choice = ui.getUserInputString("Please select an option: ");

            switch (choice) {
                case "1":
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
                    ds.saveAccount(currentAccount);
                    break;
                case "2":
                    ui.displayTransactionHistory(currentAccount);
                    break;
                case "3":
                    break;
                case "4":
                    handleCurrencyConverter();
                    break;
                case "5":

                    break;
                case "6":
                    ui.displayMessage("Thank you for your use.\nGoodbye!");
                    isRuning = false;
                    ds.saveAccount(currentAccount);
                    break;
                default:
                    ui.displayMessage("Please enter a number from 1 to 5!");
            }
        }
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

    public static void main(String[] args) {
        FinanceManager app = new FinanceManager();
        app.start();
    }
}
