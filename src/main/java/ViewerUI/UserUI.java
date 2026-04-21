package ViewerUI;

import Controller.UIManager;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class UserUI extends Application {
    UIManager manager = new UIManager();

    BorderPane displayPane = new BorderPane();
    Scene displayScene = new Scene(displayPane,800,600);
    LoginScreen loginScreen = new LoginScreen();
    BorderPane homeScreenPane = new BorderPane();
    AccountScreen accountScreen = new AccountScreen(manager);
    MenuScreen menuScreen = new MenuScreen();

    CurrencyConverterScreen currencyConverterScreen = new CurrencyConverterScreen();
    TransactionsScreen transactionsScreen = new TransactionsScreen(manager);
    MoneyGoalsScreen moneyGoalsScreen = new MoneyGoalsScreen();

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) {
        displayScene.getStylesheets().add("style.css");
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
    }

    private void updateScreens() {
         accountScreen.updateScreen();
         transactionsScreen.updateScreen();
    }

    private void attemptLogin() {
        String loginInput = loginScreen.getTextInput();
        manager.loginUser(loginInput);
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








