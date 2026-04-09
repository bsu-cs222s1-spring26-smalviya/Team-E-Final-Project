package ViewerUI;

import Controller.FinanceManager;
import Model.Transaction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AppUI extends Application {
    private FinanceManager manager;
    private TableView<Transaction> table;

    @Override
    public void start(Stage stage) {
        manager = new FinanceManager();

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your Username");

        Button loginButton = new Button("Login");

        Label output = new Label();


        TextField amountField = new TextField();
        amountField.setPromptText("Amount (+ or -)");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        Button addButton = new Button("Add transaction");

        table = new TableView<>();
        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(data.getValue().getAmount()).asObject()
            );
        TableColumn<Transaction, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data ->
                        new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription())
            );
        table.getColumns().addAll(amountColumn, descriptionColumn);


        loginButton.setOnAction(event -> {
            String username = usernameField.getText();

            manager.initAccount(username);

            output.setText("Welcome " + username);
            refreshTable();
        });

        addButton.setOnAction(event -> {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();

            String result = manager.addTransaction(amount, description);
            output.setText(result);
            refreshTable();
        });

        VBox root = new VBox(10, new Label("Finance Manager"), usernameField, loginButton,
                amountField, descriptionField, addButton, new Label("Transaction History"), table, output);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Finance Manager");
        stage.show();

    }

    private void refreshTable() {
        if (manager.getCurrentAccount() != null) {
            table.setItems(
                    javafx.collections.FXCollections.observableArrayList(
                    )
            );

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
