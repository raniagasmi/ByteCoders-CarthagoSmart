package ByteCoders.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ValidateEmail {


    public boolean isEmailValid(String email) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://email-validation-and-verification-end-to-end-checks.p.rapidapi.com/" + email))
                .header("X-RapidAPI-Key", "b3ac4b8330msh3c1f852ce29ad4dp12d96djsn477430d23750")
                .header("X-RapidAPI-Host", "email-validation-and-verification-end-to-end-checks.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the response indicates the email is valid
        // You need to parse the response body to determine validity
        String responseBody = response.body();
        boolean isValid = parseResponse(responseBody);

        return isValid;
    }

    private boolean parseResponse(String responseBody) {
        // You need to implement parsing logic based on the response body
        // This is just a placeholder example
        // You need to adapt this based on the actual response structure
        return responseBody.contains("\"status\":\"valid\"");
    }

}

