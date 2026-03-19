import Model.Account;
import Model.Transaction;
import Model.MoneyGoal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testAccountCreation() {

        Account testAccount = new Account("Alex", 500.0);

        assertNotNull(testAccount);
    }

    @Test
    public void testUsername() {

        Account testAccount = new Account("Alex", 500.0);

        assertEquals("Alex", testAccount.getUsername());
    }

    @Test
    public void testInitialBalance() {

        Account testAccount = new Account("Alex", 500.0);

        assertEquals(500.0, testAccount.getBalance());
    }

    @Test
    public void testInitialTransactionHistory() {

        Account testAccount = new Account("Alex", 500.0);

        assertTrue(testAccount.getTransactionHistory().isEmpty());
    }

    @Test
    public void testInitialMoneyGoals() {

        Account testAccount = new Account("Alex", 500.0);

        assertTrue(testAccount.getMoneyGoals().isEmpty());
    }

    @Test
    public void testAddTransaction() {

        Account testAccount = new Account("Alex", 500.0);

        Transaction t = new Transaction(50.0, "Dinner");
        testAccount.addTransaction(t);

        assertEquals(1, testAccount.getTransactionHistory().size());
        assertEquals(550.0, testAccount.getBalance());
    }

    @Test
    public void testAddMoneyGoal() {

        Account testAccount = new Account("Alex", 500.0);

        MoneyGoal goal = new MoneyGoal(
                "Laptop",
                1000.0,
                "3/15/2026"
        );

        testAccount.addMoneyGoals(goal);

        assertEquals(1, testAccount.getMoneyGoals().size());
        assertEquals("Laptop", testAccount.getMoneyGoals().get(0).getGoalName());
    }

    @Test
    public void testHasMoneyGoals() {
        Account testAccount = new Account("Fei", 500.0);
        assertFalse(testAccount.hasMoneyGoals());

        testAccount.addMoneyGoals(new MoneyGoal("iPhone", 999, "11/11/2026"));
        assertTrue(testAccount.hasMoneyGoals());
    }
}