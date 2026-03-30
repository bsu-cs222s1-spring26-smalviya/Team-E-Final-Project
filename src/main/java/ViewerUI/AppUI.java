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


        TextField amountField = new TextField();
        amountField.setPromptText("Amount (+ or -)");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        Button addButton = new Button("Add transaction");

        addButton.setOnAction(event -> {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();

            String result = manager.addTransaction(amount, description);
            output.setText(result);
        });

        VBox root = new VBox(10, title, usernameField, loginButton, amountField, descriptionField, addButton, output);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Finance Manager");
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
