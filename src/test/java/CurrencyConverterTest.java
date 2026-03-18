import Converter.CurrencyConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {

    @Test
    public void testIfReturnsJson() throws Exception {

        CurrencyConverter converter = new CurrencyConverter();

        String json = converter.getExchangeRateJson("USD", "CNY");

        assertNotNull(json);

    }

}
