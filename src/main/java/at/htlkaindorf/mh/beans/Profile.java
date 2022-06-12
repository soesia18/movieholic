package at.htlkaindorf.mh.beans;

import at.htlkaindorf.mh.command.TMDBMovieInformationCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.LinkedListMultimap;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.*;

@Data
@NoArgsConstructor
public class Profile {
    private String uid;
    private List<Integer> watchlist;
    private List<Integer> seenlist;
    private HashMap<String, Integer> seenlistGenres;
    private int seenlistWatchtime;
    private int watchlistSize;
    private int seenlistSize;
    private List<Integer> similarMoviesToSeenlist;

    public Profile(String uid, List<Integer> watchlist, List<Integer> seenlist) {
        this.uid = uid;
        this.watchlist = watchlist;
        this.seenlist = seenlist;
        this.watchlistSize = watchlist.size();
        this.seenlistSize = seenlist.size();
        this.seenlistGenres = new HashMap<>();
        this.similarMoviesToSeenlist = new ArrayList<>();

        try {
            calcStats();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calcStats() {
        TMDBMovieInformationCommand imdbInformationCommand = new TMDBMovieInformationCommand();

        for (Integer movieID : watchlist) {
            imdbInformationCommand.setMovieID(movieID);

            URI uri = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            try {
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject jsonObject = new JSONObject(response.body());

                ObjectMapper om = new ObjectMapper();
                ArrayList<Genre> genres = om.readValue(jsonObject.get("genres").toString(), om.getTypeFactory().constructCollectionType(ArrayList.class, Genre.class));
                for (Genre genre : genres) {
                    if (seenlistGenres.containsKey(genre.getName())){
                        seenlistGenres.put(genre.getName(), seenlistGenres.get(genre.getName()) + 1);
                    }else{
                        seenlistGenres.put(genre.getName(), 1);
                    }
                }
                seenlistWatchtime += jsonObject.getInt("runtime");

                uri = URI.create("https://api.themoviedb.org/3/movie/" + movieID + "/similar?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&page=1");
                request = HttpRequest.newBuilder()
                        .uri(uri)
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                jsonObject = new JSONObject(response.body());
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                HashMap<Integer, Integer> similarMovies = new HashMap<>();
                for(Object object: jsonArray){
                    JSONObject jO = (JSONObject) object;
                    int movieid = jO.getInt("id");
                    if (similarMovies.containsKey(movieid)){
                        similarMovies.put(movieid, similarMovies.get(movieid) + 1);
                    }else{
                        similarMovies.put(movieid, 1);
                    }
                }

                Set<Integer> keySet = similarMovies.keySet();
                for (Integer key : keySet) {
                    if (similarMoviesToSeenlist.size() < 5){
                        similarMoviesToSeenlist.add(key);
                        continue;
                    }
                    for(Integer mid: similarMoviesToSeenlist){
                        int amount = similarMovies.get(mid);
                        if (similarMovies.get(key) > amount){
                            similarMoviesToSeenlist.remove(mid);
                            similarMoviesToSeenlist.add(key);
                            break;
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> watchlist = Arrays.asList(54738, 18395, 54738);
        List<Integer> seenlist = Arrays.asList(54738, 56738, 54738);
        Profile profile = new Profile("187", watchlist, seenlist);

        System.out.println("Seenlist genres: " + profile.getSeenlistGenres());
        System.out.println("Seenlist watchtime: " + profile.getSeenlistWatchtime());
        System.out.println(profile.getSimilarMoviesToSeenlist());

    }
}
