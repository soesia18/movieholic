package at.htlkaindorf.mh.ressource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("/search")
public class MhSearchMovies {
    @GET
    @Path("/{searchString}/{page}")
    public Response searchMovie(@PathParam("searchString") String searchString, @PathParam("page") int page) {
        searchString = searchString.replace(" ", "%20");
        URI uri = URI.create("http://api.themoviedb.org/3/search/movie?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE&query=" + searchString + "&page=" + page);

        return getResponse(uri);
    }

    @GET
    @Path("/{id}")
    public Response getTMDBMovieInformation(@PathParam("id") int movieID) {
        URI uri = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");

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
