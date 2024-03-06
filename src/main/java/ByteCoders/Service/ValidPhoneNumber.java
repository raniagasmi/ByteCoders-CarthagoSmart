package ByteCoders.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class ValidPhoneNumber {


    public static boolean validateNumber(String phoneNumber) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://phonenumbervalidatefree.p.rapidapi.com/ts_PhoneNumberValidateTest.jsp?number=%2B216" + phoneNumber + "&country=TN"))
                .header("X-RapidAPI-Key", "b3ac4b8330msh3c1f852ce29ad4dp12d96djsn477430d23750")
                .header("X-RapidAPI-Host", "phonenumbervalidatefree.p.rapidapi.com")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());

        // Check if the response contains the key "isValidNumber"
        if (jsonResponse.has("isValidNumber")) {
            return jsonResponse.getBoolean("isValidNumber");
        } else {
            // Handle the case where "isValidNumber" key is not found
            System.out.println("Key 'isValidNumber' not found in JSON response.");
            return false;
        }
    }
}
