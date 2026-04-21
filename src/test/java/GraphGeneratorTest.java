import Graphing.AccountDataException;
import Graphing.GraphGenerator;
import Graphing.JsonLoadException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class GraphGeneratorTest {
    private static final String TEST_FILE = "test_account.json";


    @BeforeEach
    public void createTestJson() throws Exception {
        new File("data").mkdirs();

        String json = """
        {
          "username": "testuser",
          "balance": 1000.0,
          "transactionHistory": [
            {"amount": 500.0, "type": "Deposit", "date": "2026", "category": "salary", "description": "income"},
            {"amount": -200.0, "type": "Withdrawal", "date": "2026", "category": "food", "description": "expense"}
          ],
          "moneyGoals": []
        }
        """;

        FileWriter writer = new FileWriter(TEST_FILE);
        writer.write(json);
        writer.close();
    }

    @AfterEach
    public void cleanup() {
        new File(TEST_FILE).delete();
        new File("data/testuser-income_chart.png").delete();
        new File("data/testuser-expense_chart.png").delete();
    }

    @Test
    public void testGenerateIncomeChartsIsSuccess() {
        assertDoesNotThrow(() -> {
            GraphGenerator.generateCharts(TEST_FILE);
        });

        assertTrue(new File("data/testuser-income_chart.png").exists());

    }

    @Test
    public void testGenerateExpenseChartsIsSuccess() {
        assertDoesNotThrow(() -> {
            GraphGenerator.generateCharts(TEST_FILE);
        });


        assertTrue(new File("data/testuser-expense_chart.png").exists());
    }

    @Test
    public void testGenerateInvalidPath() {
        assertThrows(JsonLoadException.class, () -> {
            GraphGenerator.generateCharts("notExist.json");
        });

    }

    @Test
    public void testGenerateUmptyJson() throws Exception {
        String json = "";
        FileWriter writer = new FileWriter(TEST_FILE);
        writer.write(json);
        writer.close();
        assertThrows(AccountDataException.class,()-> {
            GraphGenerator.generateCharts(TEST_FILE);
        });
    }

    @Test
    public void testGenerateCharts_emptyTransactions() throws Exception {
        String json = """
        {
          "username": "testuser",
          "balance": 1000.0,
          "transactionHistory": [],
          "moneyGoals": []
        }
        """;

        FileWriter writer = new FileWriter(TEST_FILE);
        writer.write(json);
        writer.close();

        assertDoesNotThrow(() -> {
            GraphGenerator.generateCharts(TEST_FILE);
        });
    }

    @Test
    public void generateCharts_AllIncome() throws IOException {
        String json =  """
        {
          "username": "testuser",
          "balance": 1000.0,
          "transactionHistory": [
            {"amount": 500.0, "type": "Deposit", "date": "2026", "category": "salary", "description": "income"}
          ],
          "moneyGoals": []
        }
        """;
        FileWriter writer = new FileWriter(TEST_FILE);
        writer.write(json);
        writer.close();
        assertDoesNotThrow(()->{
            GraphGenerator.generateCharts(TEST_FILE);
        });

    }

    @Test
    public void generateCharts_AllExpense() throws IOException {
        String json =  """
        {
          "username": "testuser",
          "balance": 1000.0,
          "transactionHistory": [
            {"amount": -300.0, "type": "Withdrawal", "date": "2026", "category": "food", "description": "expense"}
          ],
          "moneyGoals": []
        }
        """;
        FileWriter writer = new FileWriter(TEST_FILE);
        writer.write(json);
        writer.close();
        assertDoesNotThrow(()->{
            GraphGenerator.generateCharts(TEST_FILE);
        });

    }



}
