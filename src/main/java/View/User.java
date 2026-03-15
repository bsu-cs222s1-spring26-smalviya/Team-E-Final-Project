package View;

import java.util.Scanner;

public class User {
    private Scanner scanner;

    public User() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println();
        System.out.println("--- Finance Manager ---");
        System.out.println("1. Record a Transaction");
        System.out.println("2. View Transaction History");
        System.out.println("3. View Financial Charts");
        System.out.println("4. Currency Converter");
        System.out.println("5. Manage Money Goals");
        System.out.println("6. Exit");
        System.out.println();
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
