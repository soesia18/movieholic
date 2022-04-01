package at.htlkaindorf.mh.ressource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("/trending")
public class MhTrendingMovies {

    @GET
    @Path("/movies")
    public Response getTrendingMovies() {
        URI uri = URI.create("http://api.themoviedb.org/3/trending/movie/week?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");

        return getResponse(uri);
    }

    private Response getResponse(URI uri) {
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
