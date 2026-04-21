package ViewerUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MoneyGoalsScreen extends VBox {

    Label goalsScreenLabel;
    Button backButton;

    public MoneyGoalsScreen() {
        goalsScreenLabel = new Label("Goals");
        backButton = new Button("Back");

        this.getChildren().setAll(
                goalsScreenLabel,
                backButton
        );

        this.setAlignment(Pos.CENTER);
    }

    public void setBackButtonAction(EventHandler<ActionEvent> action) {
        backButton.setOnAction(action);
    }
}
