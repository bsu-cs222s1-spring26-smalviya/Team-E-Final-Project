import Model.Account;
import Model.Transaction;
import Model.MoneyGoal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class AccountTest {

    @Test
    public void testAccountCreation() {

        Account account = new Account("Alex", 500.0);

        assertEquals("Alex", account.getUsername());
        assertEquals(500.0, account.getBalance());
        assertTrue(account.getTransactionHistory().isEmpty());
        assertTrue(account.getMoneyGoals().isEmpty());
    }

    @Test
    public void testAddTransaction() {

        Account account = new Account("Alex", 500.0);

        Transaction t = new Transaction(50.0, "Dinner");
        account.addTransaction(t);

        assertEquals(1, account.getTransactionHistory().size());
        assertEquals(550.0, account.getBalance());
    }

    @Test
    public void testAddMoneyGoal() {

        Account account = new Account("Alex", 500.0);

        MoneyGoal goal = new MoneyGoal(
                "Laptop",
                1000.0,
                "3/15/2026"
        );

        account.addMoneyGoals(goal);

        assertEquals(1, account.getMoneyGoals().size());
        assertEquals("Laptop", account.getMoneyGoals().get(0).getGoalName());
    }
}