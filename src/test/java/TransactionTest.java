import Model.Transaction;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

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
    public void testTransactionDateExists() {

        Transaction testTransaction = new Transaction(10, "Bus");

        assertNotNull(testTransaction.getDate());
    }
}