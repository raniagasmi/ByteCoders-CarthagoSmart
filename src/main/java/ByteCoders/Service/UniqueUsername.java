package ByteCoders.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UniqueUsername {


/*
    public boolean UsernameIsUnique(String username) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://unique-username-generator-by-pizza-api.p.rapidapi.com/"))
                .header("X-RapidAPI-Key", "b3ac4b8330msh3c1f852ce29ad4dp12d96djsn477430d23750")
                .header("X-RapidAPI-Host", "unique-username-generator-by-pizza-api.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Assuming the API response is in JSON format
        String responseBody = response.body();

        // Parse the response to determine username uniqueness
        boolean isUnique = parseResponse(responseBody);

        return isUnique;
    }

    // Method to parse the response and determine username uniqueness
    private boolean parseResponse(String responseBody) {
        // Implement your logic to parse the response and determine uniqueness
        // For example, if the response contains a field indicating uniqueness, parse it here
        // Return true if the username is unique, false otherwise
        // This is just a placeholder, you need to implement the actual logic
        return true; // Change this according to your response parsing logic
    }*/
}