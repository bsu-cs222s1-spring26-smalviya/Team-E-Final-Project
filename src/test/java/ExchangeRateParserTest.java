import Converter.ExchangeRateParser;
import Converter.JsonParserException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRateParserTest {

    @Test
    void testParseExchangeRateJson() throws JsonParserException {

        String json = """
        {
          "success": true,
          "base": "USD",
          "rates": {
            "cny": 6.9
          }
        }
        """;

        ExchangeRateParser parser = new ExchangeRateParser();

        double rate = parser.parseExchangeRateJson(json, "CNY");

        assertEquals(6.9, rate);
    }
}
