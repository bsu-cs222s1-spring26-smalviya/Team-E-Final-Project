package Converter;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

public class CurrencyConverter {
    public String getExchangeRateJson(String fromCurrency, String toCurrency) throws NetWorkException, APIException {
        try {
            BufferedReader bufferedReader = getBufferedReader(fromCurrency, toCurrency);
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (IOException e) {
            throw new NetWorkException("network error");
        }
    }

    protected BufferedReader getBufferedReader(String fromCurrency, String toCurrency) throws IOException, APIException {
        URL ConverterURL = new URL("https://currencyrateapi.com/api/latest?base=" + fromCurrency + "&quote=" + toCurrency);
        HttpURLConnection connection = (HttpURLConnection) ConverterURL.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            throw new APIException("API request failed");
        }

        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
}
