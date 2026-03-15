package Model;

import java.io.File;

public class DataStorage {

    public boolean checkLocalData(String username) {
        String fileName = username + "_data.json";
        File file = new File(fileName);
        return file.exists();
    }

    public Account loadAccount(String username) {
        return new Account(username, 0.0);//This just is a placeholder!
    }

    public void saveAccount(Account account) {
        String fileName = account.getUsername() + "_data.json";
        User ui = new User();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(account, writer);
            ui.displayMessage("Data successfully saved to: " + fileName);
        } catch (IOException e) {
            ui.displayMessage("Failed to save data. Error:" + e.getMessage());
        }


    }
}
