package at.htlkaindorf.mh.beans.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class ApiTest {
    public static void main(String[] args) {
        /*HttpRequest request = HttpRequest.newBuilder()
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
        }*/

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://imdb-api.com/de/API/Title/k_gjl52lcn/tt0126029/FullActor,FullCast,Posters,Images,Trailer,Ratings,Wikipedia,");
            CloseableHttpResponse response = client.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();

            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println(responseString);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
