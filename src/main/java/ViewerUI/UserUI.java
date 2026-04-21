package ViewerUI;

import Controller.FinanceManager;
import Model.MoneyGoal;
import Model.Transaction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.List;


public class UserUI extends Application {
    FinanceManager manager = new FinanceManager();

    BorderPane displayPane = new BorderPane();
    Scene displayScene = new Scene(displayPane);
    LoginScreen loginScreen = new LoginScreen();
    BorderPane homeScreenPane = new BorderPane();
    AccountScreen accountScreen = new AccountScreen(manager);
    MenuScreen menuScreen = new MenuScreen();

    CurrencyConverterScreen currencyConverterScreen = new CurrencyConverterScreen();
    TransactionsScreen transactionsScreen = new TransactionsScreen(manager);
    MoneyGoalsScreen moneyGoalsScreen = new MoneyGoalsScreen(manager);

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(displayScene);
        setDisplayPane(loginScreen);
        configureScreens();
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
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
        loginScreen.setLoginButtonAction( event -> attemptLogin());
    }

    private void configureHomeScreen() {
        homeScreenPane.setLeft(accountScreen);
        homeScreenPane.setRight(menuScreen);
    }

    private void configureMenuScreen() {
        menuScreen.setCurrencyConverterButtonAction(event -> setHomeDisplayPane(currencyConverterScreen));
        menuScreen.setTransactionButtonAction(event -> setHomeDisplayPane(transactionsScreen));
        menuScreen.setMoneyGoalButtonAction(event -> setHomeDisplayPane(moneyGoalsScreen));
    }

    private void configureAccountScreen() {

    }

    private void configureCurrencyScreen() {
        currencyConverterScreen.setBackButtonAction(event -> setHomeDisplayPane(menuScreen));
    }

    private void configureTransactionsScreen() {
        transactionsScreen.setBackButtonAction(event -> setHomeDisplayPane(menuScreen));
        transactionsScreen.setAddTransactionButtonAction(event -> {
            transactionsScreen.addTransaction();
            updateScreens();
        });
    }

    private void configureGoalsScreen() {
        moneyGoalsScreen.setBackButtonAction(event -> setHomeDisplayPane(menuScreen));
        moneyGoalsScreen.setAddGoalButtonAction(event -> {
            moneyGoalsScreen.addMoneyGoal();
            updateScreens();
        });
    }

    private void updateScreens() {
         accountScreen.updateScreen();
         transactionsScreen.updateScreen();
         moneyGoalsScreen.updateScreen();
    }

    private void attemptLogin() {
        String loginInput = loginScreen.getTextInput();
        manager.initAccount(loginInput);
        setDisplayPane(homeScreenPane);
    }

    private void setDisplayPane(Node displayNode) {
        displayPane.setCenter(displayNode);
        updateScreens();
    }

    private void setHomeDisplayPane(Node displayNode) {
        homeScreenPane.setRight(displayNode);
        updateScreens();
    }
}

class LoginScreen extends VBox {

    TextField textInputField;
    Button loginButton;

    public LoginScreen() {
        textInputField = new TextField();
        loginButton = new Button("Login");

        this.getChildren().setAll(
                textInputField,
                loginButton
        );

        this.setAlignment(Pos.CENTER);
    }

    public String getTextInput() {
        return textInputField.getText();
    }

    public void setLoginButtonAction(EventHandler<ActionEvent> action) {
        loginButton.setOnAction(action);
    }
}

class AccountScreen extends VBox {
    FinanceManager manager;

    Label accountScreenHeader;
    Label accountNameLabel;
    Label accountBalanceLabel;

    public AccountScreen(FinanceManager manager) {
        this.manager = manager;

        accountScreenHeader = new Label("---Account---");
        accountNameLabel = new Label("Name: ");
        accountBalanceLabel = new Label("Balance: ");

        this.getChildren().setAll(
                accountScreenHeader,
                accountNameLabel,
                accountBalanceLabel
        );

        this.setAlignment(Pos.CENTER);
        accountScreenHeader.setAlignment(Pos.CENTER);
        accountNameLabel.setAlignment(Pos.CENTER);
        accountBalanceLabel.setAlignment(Pos.CENTER);
    }

    public void updateScreen() {
        try {
            accountNameLabel.setText("Name: " + manager.getCurrentAccount().getUsername());
            accountBalanceLabel.setText("Balance: " + manager.getCurrentAccount().getBalance());
        } catch (Exception e) {
            System.out.println("Error updating AccountScreen values...");
        }
    }
}

class MenuScreen extends VBox {

    Label menuScreenLabel;
    Button button_CurrencyConverter;
    Button button_Transactions;
    Button button_MoneyGoals;

    public MenuScreen() {
        menuScreenLabel = new Label("Please Select a Menu");
        button_CurrencyConverter = new Button("Currency Converter");
        button_Transactions = new Button("Transactions");
        button_MoneyGoals = new Button("Money Goals");

        this.getChildren().setAll(
                menuScreenLabel,
                button_CurrencyConverter,
                button_Transactions,
                button_MoneyGoals
        );

        this.setAlignment(Pos.CENTER);
    }

    public void setCurrencyConverterButtonAction(EventHandler<ActionEvent> action) {
        button_CurrencyConverter.setOnAction(action);
    }

    public void setTransactionButtonAction(EventHandler<ActionEvent> action) {
        button_Transactions.setOnAction(action);
    }

    public void setMoneyGoalButtonAction(EventHandler<ActionEvent> action) {
        button_MoneyGoals.setOnAction(action);
    }
}

class CurrencyConverterScreen extends VBox {

    Label currencyConverterlabel;
    Button backButton;

    public CurrencyConverterScreen() {
        currencyConverterlabel = new Label("Currency Converter");
        backButton = new Button("Back");

        this.getChildren().setAll(
                currencyConverterlabel,
                backButton
        );

        this.setAlignment(Pos.CENTER);
    }

    public void setBackButtonAction(EventHandler<ActionEvent> action) {
        backButton.setOnAction(action);
    }
}

class TransactionsScreen extends VBox {
    FinanceManager manager;

    Label screenTitle;
    TextField amountInput;
    TextField categoryInput;
    TextField descriptionInput;
    Button addTransactionButton;
    ListView<Transaction> transactionList;
    Button backButton;

    public TransactionsScreen(FinanceManager manager) {
        this.manager = manager;

        screenTitle = new Label("Transactions");
        amountInput = new TextField();
        categoryInput = new TextField();
        descriptionInput = new TextField();
        addTransactionButton = new Button("Add Transaction");
        transactionList = new ListView<>();
        backButton = new Button("Back");

        this.getChildren().setAll(
                screenTitle,
                amountInput,
                categoryInput,
                descriptionInput,
                addTransactionButton,
                transactionList,
                backButton
        );

        configureVisualDetails();
    }

    private void configureVisualDetails() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

        amountInput.setPromptText("Amount for the transaction (- value for withdrawal)");
        categoryInput.setPromptText("Category of transaction (i.e. \"Rent\" or \"Food\")");
        descriptionInput.setPromptText("Description/note for the transaction");
    }

    public void setBackButtonAction(EventHandler<ActionEvent> action) {
        backButton.setOnAction(action);
    }

    public void setAddTransactionButtonAction(EventHandler<ActionEvent> action) {
        addTransactionButton.setOnAction(action);
    }

    public void addTransaction() {
        try {
            double transactionAmount = Double.parseDouble(amountInput.getText());
            String transactionCategory = categoryInput.getText();
            String transactionDescription = descriptionInput.getText();

            Transaction transaction = new Transaction(transactionAmount, transactionCategory, transactionDescription);

            manager.getCurrentAccount().addTransaction(transaction);

            amountInput.clear();
            categoryInput.clear();
            descriptionInput.clear();
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input...");
        }
    }

    public void updateScreen() {
        try {
            List<Transaction> transactions = manager.getCurrentAccount().getTransactionList();
            transactionList.getItems().setAll(transactions);
        } catch (Exception e) {
            System.out.println("Error updating TransactionScreen values...");
        }
    }
}

class MoneyGoalsScreen extends VBox {
    FinanceManager manager;

    Label goalsScreenLabel;
    TextField goalNameInput;
    TextField goalAmountInput;
    TextField goalDeadlineInput;
    Button addGoalButton;
    ListView<MoneyGoal> moneyGoalsList;
    Button backButton;

    public MoneyGoalsScreen(FinanceManager manager) {
        this.manager = manager;

        goalsScreenLabel = new Label("Goals");
        goalNameInput = new TextField();
        goalAmountInput = new TextField();
        goalDeadlineInput = new TextField();
        addGoalButton = new Button("Add Goal");
        moneyGoalsList = new ListView<>();
        backButton = new Button("Back");

        this.getChildren().setAll(
                goalsScreenLabel,
                goalNameInput,
                goalAmountInput,
                goalDeadlineInput,
                addGoalButton,
                moneyGoalsList,
                backButton
        );

        configureVisualDetails();
    }

    private void configureVisualDetails() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

        goalNameInput.setPromptText("The name for the goal");
        goalAmountInput.setPromptText("The target amount to reach for the goal");
        goalDeadlineInput.setPromptText("The deadline for the goal to be reached by");
    }

    public void setBackButtonAction(EventHandler<ActionEvent> action) {
        backButton.setOnAction(action);
    }

    public void setAddGoalButtonAction(EventHandler<ActionEvent> action) {
        addGoalButton.setOnAction(action);
    }

    public void addMoneyGoal() {
        try {
            String goalName = goalNameInput.getText();
            double goalAmount = Double.parseDouble(goalAmountInput.getText());
            String goalDeadline = goalDeadlineInput.getText();

            MoneyGoal moneyGoal = new MoneyGoal(goalName, goalAmount, goalDeadline);

            manager.getCurrentAccount().addMoneyGoals(moneyGoal);

            goalNameInput.clear();
            goalAmountInput.clear();
            goalDeadlineInput.clear();
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input...");
        }
    }

    public void updateScreen() {
        try {
            List<MoneyGoal> moneyGoals = manager.getCurrentAccount().getMoneyGoalList();
            moneyGoalsList.getItems().setAll(moneyGoals);
        } catch (Exception e) {
            System.out.println("Error updating MoneyGoalsScreen values...");
        }
    }
}