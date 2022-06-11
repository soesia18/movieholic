package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
* GenreCommand class extends {@link ApiCommand} which is here to get all Genres from TheMovieDB.
 * @author Simon
 * @version 1.0
 * @since last update: 23.05.2022
 */
public class GenreCommand extends ApiCommand {
    /**
     * Execute the command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/genre/movie/list?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");

        return IO_Response.getResponse(super.command);
    }
}
