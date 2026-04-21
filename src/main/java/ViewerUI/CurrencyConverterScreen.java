package ViewerUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CurrencyConverterScreen extends VBox {

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
