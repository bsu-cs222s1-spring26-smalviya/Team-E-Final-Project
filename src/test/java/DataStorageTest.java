import Model.DataStorage;
import Model.Account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataStorageTest {

    @Test
    public void testCheckLocalDataWhenFileDoesNotExist() {

        DataStorage storage = new DataStorage();

        boolean exists = storage.checkLocalData("testUser");

        assertFalse(exists);
    }

    @Test
    public void testLoadAccountCreatesAccount() {

        DataStorage storage = new DataStorage();

        Account account = storage.loadAccount("Alex");

        assertNotNull(account);
        assertEquals("Alex", account.getUsername());
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void testSaveAccountDoesNotThrowError() {

        DataStorage storage = new DataStorage();

        Account account = new Account("Alex", 100);

        assertDoesNotThrow(() -> {
            storage.saveAccount(account);
        });
    }
}