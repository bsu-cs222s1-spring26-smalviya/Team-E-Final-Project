import Model.Transaction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testTransactionCreation() {

        Transaction testTransaction = new Transaction(-25.5, "food", "Lunch");

        assertNotNull(testTransaction);
    }

    @Test
    public void testTransactionAmount() {

        Transaction testTransaction = new Transaction(45, "food", "Fast Food");

        assertEquals(45, testTransaction.getAmount());
    }

    @Test
    public void testTransactionWithdrawalType() {

        Transaction withdrawalTransaction = new Transaction(-15, "Gambling", "Lost Gambling");

        assertEquals("Withdrawal", withdrawalTransaction.getTransactionType());
    }

    @Test
    public void testTransactionDepositType() {

        Transaction depositTransaction = new Transaction(15, "Gambling", "Won Gambling");

        assertEquals("Deposit", depositTransaction.getTransactionType());
    }

    @Test
    public void testDescription() {

        Transaction testTransaction = new Transaction(12, "Gym", "Gym");

        assertEquals("Gym", testTransaction.getDescription());
    }

    @Test
    public void testTransactionDateExists() {

        Transaction testTransaction = new Transaction(10, "Transport", "Bus");

        assertNotNull(testTransaction.getDate());
    }
}