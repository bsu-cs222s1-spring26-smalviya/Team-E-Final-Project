package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import View.User;

public class DataStorage {
    private Gson gson = new Gson();

    public DataStorage() {

    }

    public boolean checkLocalData(String username) {
        String fileName = username + "_data.json";
        File file = new File(fileName);
        return file.exists();
    }

    public Account loadAccount(String username) {
        String fileName = username + "_data.json";
        File file = new File(fileName);
        User ui = new User();

        if (!file.exists()) {
            return null;
        }

        try (FileReader reader = new FileReader(file)) {
            Account account = gson.fromJson(reader, Account.class);
            ui.displayMessage("Data successfully loaded for: " + username);

            return account;
        } catch (IOException e) {
            ui.displayMessage("Failed to load data. Error: " + e.getMessage());
            return null;
        }
    }

    public void saveAccount(Account account) {
        String fileName = account.getUsername() + "_data.json";
        User ui = new User();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(account, writer);
            ui.displayMessage("Data successfully saved.");
        } catch (IOException e) {
            ui.displayMessage("Failed to save data. Error:" + e.getMessage());
        }


    }
}
