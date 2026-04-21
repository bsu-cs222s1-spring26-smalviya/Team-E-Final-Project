package ViewerUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginScreen extends VBox {

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

