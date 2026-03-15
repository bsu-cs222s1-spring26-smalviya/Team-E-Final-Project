import Model.Transaction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testTransactionCreation() {

        Transaction t = new Transaction(25.5, "Food", "Lunch");

        assertEquals(25.5, t.getAmount());
        assertEquals("Food", t.getCategory());
        assertEquals("Lunch", t.getDescription());
    }

    @Test
    public void testTransactionDateExists() {

        Transaction t = new Transaction(10, "Transport", "Bus");

        assertNotNull(t.getDate());
    }
}