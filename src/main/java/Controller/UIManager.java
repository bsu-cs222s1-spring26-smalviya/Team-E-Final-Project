package Controller;

import Model.Account;
import Model.DataStorage;

public class UIManager {
    private Account currentAccount;

    public UIManager() {
        currentAccount = null;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public Account loginUser(String username) {
        Account userAccount = null;
        try {
            userAccount = DataStorage.loadAccount(username);
            System.out.println("Logged in " + userAccount.getUsername());
        } catch (Exception e) {
            System.out.println("Created new account...");
            userAccount = new Account(username, 0);
        }
        currentAccount = userAccount;
        return userAccount;
    }
}