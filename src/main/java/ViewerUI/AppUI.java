package ViewerUI;

import Controller.FinanceManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AppUI extends Application {
    private FinanceManager manager;

    @Override
    public void start(Stage stage) {
        manager = new FinanceManager();
        Label title  = new Label("Finance Manager");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your Username");

        Button loginButton = new Button("Login");

        Label output = new Label();

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();

            output.setText("Welcome " + username);

        });
        VBox root = new VBox(10, title, usernameField, loginButton, output);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Finance Manager");
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
