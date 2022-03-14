package at.htlkaindorf.mh.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class ApiTest {
    public static void main(String[] args) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.themoviedb.org/3/search/movie?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&query=Monkey&page=1&include_adult=false"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            SearchResult searchResult = gson.fromJson(response.body(), SearchResult.class);

            System.out.println(searchResult);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
