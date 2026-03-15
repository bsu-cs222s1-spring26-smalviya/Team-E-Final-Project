package Controller;

import Model.Account;
import View.User;
import Model.DataStorage;

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

        String username = ui.getUserInput("Welcome!\nPlease enter your username.");

        boolean hasSaveData = ds.checkLocalData(username);

        if (hasSaveData) {
            ui.displayMessage("Loading your saved data...");
            this.currentAccount = ds.loadAccount(username);

        } else {
            ui.displayMessage("You are a new customer!");
            ui.displayMessage("Creating a new account for you...");

            this.currentAccount = new Account(username, 0.0);
        }

        ui.displayMessage("Welcome to the Finance Manager, " + currentAccount.getUsername() + "!");
        while (isRuning) {
            ui.displayMainMenu();
            String choice = ui.getUserInput("Please select an option: ");

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
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

    public static void main(String[] args) {
        FinanceManager app = new FinanceManager();
        app.start();
    }
}
