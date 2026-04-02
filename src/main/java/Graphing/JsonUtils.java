package Graphing;

import Model.Account;
import View.User;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;

public class JsonUtils {

    public static Account readUser(String path) throws Exception {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(path), Account.class);
    }

}