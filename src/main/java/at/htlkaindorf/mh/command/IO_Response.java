package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IO_Response {
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
