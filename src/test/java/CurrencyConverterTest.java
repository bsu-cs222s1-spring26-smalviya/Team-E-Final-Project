import Converter.CurrencyConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {

    @Test
    public void testGetExchangeRateJson() throws Exception {

        CurrencyConverter converter = new CurrencyConverter();

        String json = converter.getExchangeRateJson("USD", "CNY");

        assertNotNull(json);

        assertTrue(json.contains("rates"));

        assertTrue(json.contains("cny") || json.contains("CNY"));
    }
}
