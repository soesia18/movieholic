package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 * NowPlayingCommand class extends {@link ApiCommand} which is here to get all Now Playing Movies from TheMovieDB.
 * @author Simon
 * @version 1.0
 * @since last update: 23.05.2022
 */
public class NowPlayingCommand extends ApiCommand{

    /**
     * Execute the command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/now_playing?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE&page=1");
        return IO_Response.getResponse(super.command);
    }
}
