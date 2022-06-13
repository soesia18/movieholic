package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * IO_Response is here to create a {@link Response} from a {@link URI} and execute it. <br>
 * The {@link HttpRequest} is build with the {@link URI} and copy the body in the {@link Response}.
 * @author Simon
 * @version 1.0
 * @since last update: 02.05.2022
 */
public class IO_Response {
    /**
     * Create a {@link HttpRequest} from the {@link URI} and execute it.
     * @param uri the {@link URI} with the right api call
     * @return a {@link Response} from the executed {@link HttpRequest} body
     */
    public static Response getResponse(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return Response.ok(response.body()).build();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Response.noContent().build();
    }
}
