import Model.Transaction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testTransactionCreation() {

        Transaction testTransaction = new Transaction(-25.5, "Lunch");

        assertNotNull(testTransaction);
    }

    @Test
    public void testTransactionAmount() {

        Transaction testTransaction = new Transaction(45, "Fast Food");

        assertEquals(45, testTransaction.getAmount());
    }

    @Test
    public void testTransactionWithdrawalType() {

        Transaction withdrawalTransaction = new Transaction(-15, "Lost Gambling");

        assertEquals("Withdrawal", withdrawalTransaction.getTransactionType());
    }

    @Test
    public void testTransactionDepositType() {

        Transaction depositTransaction = new Transaction(15, "Won Gambling");

        assertEquals("Deposit", depositTransaction.getTransactionType());
    }

    @Test
    public void testDescription() {

        Transaction testTransaction = new Transaction(12, "Gym");

        assertEquals("Gym", testTransaction.getDescription());
    }

    @Test
    public void testTransactionDateExists() {

        Transaction testTransaction = new Transaction(10, "Bus");

        assertNotNull(testTransaction.getDate());
    }
}