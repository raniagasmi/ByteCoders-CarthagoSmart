package bytecoders.pidev.services;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QrCodeGenerator {
    public static void main(String[] args) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://qr-code-generator20.p.rapidapi.com/generateadvancebase64?data=1234&size=500&margin=10&label=My%20label&label_size=20&label_alignment=center&foreground_color=FF2400&background_color=00DBFF"))
                    .header("X-RapidAPI-Key", "b3ac4b8330msh3c1f852ce29ad4dp12d96djsn477430d23750")
                    .header("X-RapidAPI-Host", "qr-code-generator20.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
