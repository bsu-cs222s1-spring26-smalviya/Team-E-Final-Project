package ViewerUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuScreen extends VBox {

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