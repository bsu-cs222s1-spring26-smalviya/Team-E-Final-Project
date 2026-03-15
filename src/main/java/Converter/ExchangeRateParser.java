package Converter;

import org.json.JSONException;
import org.json.JSONObject;

public class ExchangeRateParser {
    public double parseExchangeRateJson(String exchangeRateJson, String toCurrency)throws JsonParserException{
        toCurrency = toCurrency.toLowerCase();
        try {
            JSONObject jsonObject = new JSONObject(exchangeRateJson);
            JSONObject rateObject = jsonObject.getJSONObject("rates");
            double rate = rateObject.getDouble(toCurrency);
            return rate;
        }catch (JSONException e){
            throw new JsonParserException("Parse ExchangeRate Json Error");
        }


    }
}
