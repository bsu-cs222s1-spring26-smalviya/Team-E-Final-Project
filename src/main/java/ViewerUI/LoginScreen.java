package ViewerUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginScreen extends VBox {

    Label welcomeLabel;
    TextField textInputField;
    Button loginButton;

    public LoginScreen() {
        welcomeLabel = new Label("Welcome to FinanceManager");
        welcomeLabel.getStyleClass().add("title-label");

        textInputField = new TextField();
        textInputField.setPromptText("Username");

        loginButton = new Button("Login");

        this.getStyleClass().add("content-vbox");

        this.getChildren().setAll(
                welcomeLabel,
                textInputField,
                loginButton
        );

        this.setAlignment(Pos.CENTER);
        this.setFocusTraversable(true);
        javafx.application.Platform.runLater(() -> this.requestFocus());
    }

    public String getTextInput() {
        return textInputField.getText();
    }

    public void setLoginButtonAction(EventHandler<ActionEvent> action) {
        loginButton.setOnAction(action);
    }
}

