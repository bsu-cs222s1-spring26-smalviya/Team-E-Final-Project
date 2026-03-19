import Model.DataStorage;
import Model.Account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataStorageTest {

    @Test
    public void testCheckLocalDataWhenFileDoesNotExist() {

        DataStorage storage = new DataStorage();

        boolean exists = storage.checkLocalData("testUser");

        assertFalse(exists);
    }

    @Test
    public void testLoadAccountReturnsNullWhenFileNotFound() throws Exception {
        DataStorage storage = new DataStorage();
        Account account = storage.loadAccount("SomeFakeUserThatDoesNotExist123");
        assertNull(account);
    }

    @Test
    public void testSaveAccountDoesNotThrowError() {

        DataStorage storage = new DataStorage();

        Account account = new Account("Alex", 100);

        assertDoesNotThrow(() -> storage.saveAccount(account));
    }
}