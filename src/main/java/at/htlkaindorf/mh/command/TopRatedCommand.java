package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * TopRatedCommand class extends {@link ApiCommand} which is here to get all Top Rated Movies from TheMovieDB.
 * @author Simon
 * @version 1.0
 * @since last update: 23.05.2022
 */
public class TopRatedCommand extends ApiCommand {

    /**
     * Execute the API command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/top_rated?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&page=1");
        return IO_Response.getResponse(super.command);
    }
}
