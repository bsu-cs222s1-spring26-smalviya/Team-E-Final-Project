package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class DataStorage {
    private static final Gson gson = new Gson();
    private static final String DATA_DIR = "data" + File.separator;

    public static boolean checkLocalData(String username) {
        return getUserFile(username).exists();
    }

    public static Account loadAccount(String username) throws IOException {
        File file = getUserFile(username);
        if (!file.exists()) {
            return null;
        }
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Account.class);
        }
    }

    public static void saveAccount(Account account) throws IOException {
        File dir = new File(DATA_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Failed to create directory: " + DATA_DIR);
        }
        File file = getUserFile(account.getUsername());
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(account, writer);
        }
    }

    public static Account readUser(String path) throws Exception {
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, Account.class);
        }catch(Exception e){
            throw new ReadUserException("error occured while reading users info");
        }
    }

    private static File getUserFile(String username) {
        return new File(DATA_DIR + username + "_data.json");
    }
}
