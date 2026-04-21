import Converter.ExchangeRateParser;
import Converter.JsonParserException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRateParserTest {

    private static ExchangeRateParser parser = new ExchangeRateParser();
    @Test
    public void testParsedExchangeRate() throws JsonParserException {

        String json = """
        {
          "success": true,
          "base": "usd",
          "rates": {
            "cny": 6.9
          }
        }
        """;



        double rate = parser.parseExchangeRateJson(json, "cny");

        assertEquals(6.9, rate);
    }

    @Test
    public void testUpperCaseCurrencyFormat() throws JsonParserException {
        String json = """
        {
          "success": true,
          "base": "usd",
          "rates": {
            "cny": 6.9
          }
        }
        """;



        double rate = parser.parseExchangeRateJson(json, "CNY");

        assertEquals(6.9, rate);
    }

    @Test
    public void testInvalidJson(){
        String json = """
        error json
        """;
        assertThrows(JsonParserException.class,()->{
            double rate = parser.parseExchangeRateJson(json, "CNY");
        });

    }


}
