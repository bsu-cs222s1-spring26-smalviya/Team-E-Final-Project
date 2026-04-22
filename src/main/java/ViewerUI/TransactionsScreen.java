package ViewerUI;

import Controller.UIManager;
import Model.Transaction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

class TransactionsScreen extends VBox {
    UIManager manager;

    Label screenTitle;
    TextField amountInput;
    TextField categoryInput;
    TextField descriptionInput;
    Button addTransactionButton;
    ListView<Transaction> transactionList;
    Button backButton;

    public TransactionsScreen(UIManager manager) {
        this.manager = manager;

        screenTitle = new Label("Transactions");
        screenTitle.getStyleClass().add("title-label");

        amountInput = new TextField();
        categoryInput = new TextField();
        descriptionInput = new TextField();
        addTransactionButton = new Button("Add Transaction");
        transactionList = new ListView<>();
        backButton = new Button("Back");

        this.getStyleClass().add("content-vbox");

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
        categoryInput.setPromptText("Category of transaction (i.e \"Rent\" or \"Food\")");
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
