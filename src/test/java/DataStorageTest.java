import Model.DataStorage;
import Model.Account;

import Model.ReadUserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataStorageTest {

    @Test
    public void testCheckLocalDataWhenFileDoesNotExist() {

        boolean exists = DataStorage.checkLocalData("testUser");

        assertFalse(exists);
    }

    @Test
    public void testLoadAccountReturnsNullWhenFileNotFound() throws Exception {

        Account account = DataStorage.loadAccount("SomeFakeUserThatDoesNotExist123");

        assertNull(account);
    }

    @Test
    public void testSaveAccountDoesNotThrowError() {

        Account account = new Account("Alex", 100);

        assertDoesNotThrow(() -> DataStorage.saveAccount(account));
    }

    @Test
    public void testCheckLocalDataWhenFileExists() throws Exception {

        Account account = new Account("daniel", 100);
        DataStorage.saveAccount(account);

        assertTrue(DataStorage.checkLocalData("daniel"));
    }

    @Test
    public void testReadUser() throws Exception {

        Account account = new Account("daniel", 100);

        DataStorage.saveAccount(account);

        Account loaded = DataStorage.readUser("data/Bob_data.json");

        assertNotNull(loaded);

        assertEquals("daniel", loaded.getUsername());
    }

    @Test
    public void testReadUserInvalidPath(){
        assertThrows(ReadUserException.class,()->{
            DataStorage.readUser("invalid_path.json");
        });
    }
}