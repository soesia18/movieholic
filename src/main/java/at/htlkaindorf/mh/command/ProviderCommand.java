package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 * ProviderCommand class extends {@link MovieIDCommand} which is here to get all Providers from TheMovieDB.
 * @author Simon
 * @version 1.0
 * @since last update: 02.05.2022
 */
public class ProviderCommand extends MovieIDCommand {
    /**
     * set the movie id
     * @param movieID the movie id
     */
    @Override
    public void setMovieID(int movieID) {
        super.setMovieID(movieID);
    }

    /**
     * Execute the API command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "/watch/providers?api_key=e2b8d803a857305a89319b778145cfa0");
        return IO_Response.getResponse(super.command);
    }
}
