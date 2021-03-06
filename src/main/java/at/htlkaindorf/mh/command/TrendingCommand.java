package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.Data;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 * SearchCompanyCommand class extends {@link ApiCommand} which is here to get all Trending from TheMovieDB.
 * @author Simon
 * @version 1.0
 * @since last update: 13.06.2022
 */
public class TrendingCommand extends ApiCommand {
    /**
     * Execute the API command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command =
                URI.create("http://api.themoviedb.org/3/trending/movie/week?" +
                        "api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");
        return IO_Response.getResponse(super.command);
    }
}
