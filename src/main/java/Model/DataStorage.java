package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class DataStorage {
    private Gson gson = new Gson();

    public boolean checkLocalData(String username) {
        return new File(username + "_data.json").exists();
    }

    public Account loadAccount(String username) throws IOException {
        File file = new File(username + "_data.json");
        if (!file.exists()) {
            return null;
        }
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Account.class);
        }
    }

    public void saveAccount(Account account) throws IOException {
        String fileName = account.getUsername() + "_data.json";
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(account, writer);
        }
    }
}
