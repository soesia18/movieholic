package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.command.CommandController;
import at.htlkaindorf.mh.command.TrendingCommand;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * MhTrendingMovies is here to get all Trending Movies from TheMovieDB.
 */
@Path("/trending")
public class MhTrendingMovies {

    TrendingCommand trendingCommand = new TrendingCommand();

    /**
     * Execute the API command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @GET
    @Path("/movies")
    public Response getTrendingMovies() {
        return CommandController.getInstance().execute(trendingCommand);
    }
}
