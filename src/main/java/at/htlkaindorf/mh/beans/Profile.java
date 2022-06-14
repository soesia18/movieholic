package at.htlkaindorf.mh.beans;

import at.htlkaindorf.mh.command.TMDBMovieInformationCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

/**
 * <b>Movieholic</b><br><br>
 * <b>Bean Class for Profile</b> <br>
 * @author David
 * @version 1.0
 * @since last update: 13.06.2022
 */
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
    private List<Integer> similarMoviesToWatchlist;

    private int estimatedWatchlistWatchtime;

    /**
     * Constructor for Profile
     * @param {@link String} uid
     * @param {@link List}<{@link Integer}> watchlist
     * @param {@link List}<{@link Integer}> seenlist
     */
    public Profile(String uid, List<Integer> watchlist, List<Integer> seenlist) {
        this.uid = uid;
        this.watchlist = watchlist;
        this.seenlist = seenlist;
        this.watchlistSize = watchlist.size();
        this.seenlistSize = seenlist.size();
        this.seenlistGenres = new HashMap<>();
        this.similarMoviesToSeenlist = new ArrayList<>();
        this.similarMoviesToWatchlist = new ArrayList<>();

        calcStatsSeenlist();
        calcStatsWatchlist();
    }

    /**
     * Calculates the stats of the seenlist
     */
    private void calcStatsSeenlist() {
        TMDBMovieInformationCommand imdbInformationCommand = new TMDBMovieInformationCommand();

        HashMap<Integer, Integer> similarMovies = new HashMap<>();
        for (Integer movieID : seenlist) {
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
                    if (seenlistGenres.containsKey(genre.getName())) {
                        seenlistGenres.put(genre.getName(), seenlistGenres.get(genre.getName()) + 1);
                    } else {
                        seenlistGenres.put(genre.getName(), 1);
                    }
                }
                seenlistWatchtime += jsonObject.getInt("runtime");

                uri = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "/similar?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&page=1");
                HttpRequest req = HttpRequest.newBuilder()
                        .uri(uri)
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                response = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
                jsonObject = new JSONObject(response.body());
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (Object object : jsonArray) {
                    JSONObject jO = (JSONObject) object;
                    int movieid = jO.getInt("id");
                    if (similarMovies.containsKey(movieid)) {
                        similarMovies.put(movieid, similarMovies.get(movieid) + 1);
                    } else {
                        similarMovies.put(movieid, 1);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        Set<Integer> keySet = similarMovies.keySet();
        for (Integer key : keySet) {
            if (similarMoviesToSeenlist.size() < 5) {
                similarMoviesToSeenlist.add(key);
                continue;
            }
            for (Integer mid : similarMoviesToSeenlist) {
                int amount = similarMovies.get(mid);
                if (similarMovies.get(key) > amount) {
                    similarMoviesToSeenlist.remove(mid);
                    similarMoviesToSeenlist.add(key);
                    break;
                }
            }
        }
    }

    /**
     * Calculates the stats of the watchlist
     */
    public void calcStatsWatchlist(){
        TMDBMovieInformationCommand imdbInformationCommand = new TMDBMovieInformationCommand();

        HashMap<Integer, Integer> similarMovies = new HashMap<>();
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
                estimatedWatchlistWatchtime += jsonObject.getInt("runtime");

                uri = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "/similar?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&page=1");
                request = HttpRequest.newBuilder()
                        .uri(uri)
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                jsonObject = new JSONObject(response.body());
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (Object object : jsonArray) {
                    JSONObject jO = (JSONObject) object;
                    int movieid = jO.getInt("id");
                    if (similarMovies.containsKey(movieid)) {
                        similarMovies.put(movieid, similarMovies.get(movieid) + 1);
                    } else {
                        similarMovies.put(movieid, 1);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        Set<Integer> keySet = similarMovies.keySet();
        for (Integer key : keySet) {
            if (similarMoviesToWatchlist.size() < 5) {
                similarMoviesToWatchlist.add(key);
                continue;
            }
            for (Integer mid : similarMoviesToWatchlist) {
                int amount = similarMovies.get(mid);
                if (similarMovies.get(key) > amount) {
                    similarMoviesToWatchlist.remove(mid);
                    similarMoviesToWatchlist.add(key);
                    break;
                }
            }
        }
    }
}
