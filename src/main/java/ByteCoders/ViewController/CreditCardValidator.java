package ByteCoders.ViewController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CreditCardValidator {
    private String apiKey = "9783d671-9ac6-40f5-9f43-6af06885aee4";
    private String endpoint = "https://api.apistacks.com/v1/validatecard?api_key=9783d671-9ac6-40f5-9f43-6af06885aee4&cardnumber=";


    //{"status":"ok","timestamp":1709682272490,"data":{"niceType":"Visa","type":"visa","gaps":[4,8,12],"lengths":[16,18,19],"code":{"name":"CVV","size":3},"matchStrength":1}}
    public String validateCard(String cardNumber) {
        try {
            URL url = new URL(endpoint + cardNumber);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = br.readLine();
            System.out.println(result);
            return result;
        } catch (Exception e) {
        }
        return "error";
    }

}
