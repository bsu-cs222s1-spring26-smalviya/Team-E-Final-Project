package ViewerUI;

import Controller.UIManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AccountScreen extends VBox {
    UIManager manager;

    Label accountScreenHeader;
    Label accountNameLabel;
    Label accountBalanceLabel;

    public AccountScreen(UIManager manager) {
        this.manager = manager;

        accountScreenHeader = new Label("Account Information");
        accountScreenHeader.getStyleClass().add("title-label");

        accountNameLabel = new Label("Name: ");
        accountBalanceLabel = new Label("Balance: ");

        this.getStyleClass().add("account-vbox");

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
