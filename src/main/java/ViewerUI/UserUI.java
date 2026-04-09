package ViewerUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserUI extends Application {
    BorderPane displayPane = new BorderPane();
    Scene displayScene = new Scene(displayPane);
    VBox loginScreen = new VBox();
    BorderPane homeScreen = new BorderPane();
    VBox accountScreen = new VBox();
    VBox menuScreen = new VBox();

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(displayScene);
        setDisplayPane(loginScreen);
        configureLoginScreen();
        configureHomeScreen();
        primaryStage.show();
    }

    private void configureLoginScreen() {
        TextField loginTextField = new TextField();
        Button loginButton = new Button("Login");

        loginScreen.getChildren().addAll(loginTextField, loginButton);
        loginScreen.setAlignment(Pos.CENTER);

        loginButton.setOnAction( event -> setDisplayPane(homeScreen));
    }

    private void configureHomeScreen() {
        Label menuScreenLabel = new Label("Please Select a Menu");
        Button menuButton_CurrencyConverter = new Button("Currency Converter");
        Button menuButton_Transactions = new Button("Transactions");
        Button menuButton_MoneyGoals = new Button("Money Goals");
        Label accountScreenlabel = new Label("-Account-");

        accountScreen.getChildren().setAll(accountScreenlabel);
        menuScreen.getChildren().setAll(menuScreenLabel, menuButton_CurrencyConverter, menuButton_Transactions, menuButton_MoneyGoals);

        homeScreen.setLeft(accountScreen);
        homeScreen.setRight(menuScreen);
    }

    private void setDisplayPane(Node displayNode) {
        displayPane.setCenter(displayNode);
    }
}
