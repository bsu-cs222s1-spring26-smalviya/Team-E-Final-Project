package ViewerUI;

import Controller.UIManager;
import Model.Account;
import Model.Transaction;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class UserUI extends Application {
    UIManager manager = new UIManager();

    BorderPane displayPane = new BorderPane();
    Scene displayScene = new Scene(displayPane);
    VBox loginScreen = new VBox();
    BorderPane homeScreen = new BorderPane();
    VBox accountScreen = new VBox();
    VBox menuScreen = new VBox();

    VBox currencyScreen = new VBox();
    VBox transactionsScreen = new VBox();
    VBox goalsScreen = new VBox();

    private List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(displayScene);
        setDisplayPane(loginScreen);
        configureScreens();
        primaryStage.show();
    }

    private void configureScreens() {
        configureLoginScreen();
        configureHomeScreen();
        configureAccountScreen();
        configureMenuScreen();
        configureCurrencyScreen();
        configureTransactionsScreen();
        configureGoalsScreen();
    }

    private void configureLoginScreen() {
        TextField loginTextField = new TextField();
        Button loginButton = new Button("Login");

        loginScreen.getChildren().addAll(loginTextField, loginButton);
        loginScreen.setAlignment(Pos.CENTER);

        loginButton.setOnAction( event -> attemptLogin(loginTextField.getText()));
    }

    private void configureHomeScreen() {
        homeScreen.setLeft(accountScreen);
        setHomeDisplayPane(menuScreen);
    }

    private void configureMenuScreen() {
        Label menuScreenLabel = new Label("Please Select a Menu");
        Button menuButton_CurrencyConverter = new Button("Currency Converter");
        Button menuButton_Transactions = new Button("Transactions");
        Button menuButton_MoneyGoals = new Button("Money Goals");

        menuButton_CurrencyConverter.setOnAction(event -> setHomeDisplayPane(currencyScreen));
        menuButton_Transactions.setOnAction(event -> setHomeDisplayPane(transactionsScreen));
        menuButton_MoneyGoals.setOnAction(event -> setHomeDisplayPane(goalsScreen));

        menuScreen.getChildren().setAll(menuScreenLabel, menuButton_CurrencyConverter, menuButton_Transactions, menuButton_MoneyGoals);
    }

    private void configureAccountScreen() {
        Label accountScreenHeader = new Label("---Account---");
        Label accountNameLabel = new Label("Name: ");
        Label accountBalanceLabel = new Label("Balance: ");

        accountScreen.getChildren().setAll(accountScreenHeader, accountNameLabel, accountBalanceLabel);
        accountScreen.setAlignment(Pos.TOP_CENTER);
        accountScreenHeader.setAlignment(Pos.CENTER);
        accountNameLabel.setAlignment(Pos.CENTER);
        accountBalanceLabel.setAlignment(Pos.CENTER);
    }

    private void configureCurrencyScreen() {
        Label currencyConverterlabel = new Label("Currency Converter");
        Button backButton = new Button("Back");

        backButton.setOnAction(event -> setHomeDisplayPane(menuScreen));

        currencyScreen.getChildren().setAll(currencyConverterlabel, backButton);
        currencyScreen.setAlignment(Pos.CENTER);
    }

    private void configureTransactionsScreen() {
        Label title = new Label("Transactions");
        Label balanceLabel = new Label("Balance: $0");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount (+ deposit, - withdrawal)");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        Button addButton = new Button("Add Transaction");

        ListView<Transaction> transactionList = new ListView<>();


        addButton.setOnAction(event -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();

                Transaction transaction = new Transaction(amount, description);

                transactions.add(transaction);
                transactionList.getItems().add(transaction);

                transactionList.getItems().addAll(transactions);
                double total = transactions.stream().mapToDouble(Transaction::getAmount).sum();

                balanceLabel.setText("Balance: $" + total);

                amountField.clear();
                descriptionField.clear();

            }
            catch (NumberFormatException exception) {
                System.out.println("Invalid number");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> setHomeDisplayPane(menuScreen));

        transactionsScreen.getChildren().setAll(
                title,
                balanceLabel,
                amountField,
                descriptionField,
                addButton,
                transactionList,
                backButton
        );

        transactionsScreen.setAlignment(Pos.CENTER);
        transactionsScreen.setSpacing(10);
    }

    private void configureGoalsScreen() {
        Label goalsScreenLabel = new Label("Goals");
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> setHomeDisplayPane(menuScreen));

        goalsScreen.getChildren().setAll(goalsScreenLabel, backButton);
        goalsScreen.setAlignment(Pos.CENTER);
    }

    private void updateScreenValues() {
        updateAccountScreen();
    }

    private void updateAccountScreen() {
        javafx.collections.ObservableList<Node> accountScreenElements = accountScreen.getChildren();
        Node accountName = accountScreenElements.get(1);
        Node accountBalance = accountScreenElements.get(2);
    }

    private void attemptLogin(String username) {
        Account userAccount = manager.loginUser(username);

        if (userAccount != null) {
            setDisplayPane(homeScreen);
        } else {
            setDisplayPane(homeScreen);
        }
    }

    private void setDisplayPane(Node displayNode) {
        displayPane.setCenter(displayNode);
    }

    private void setHomeDisplayPane(Node displayNode) {
        homeScreen.setRight(displayNode);
    }
}

class loginScreen extends VBox {

}