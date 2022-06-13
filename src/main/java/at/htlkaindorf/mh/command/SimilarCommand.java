package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 * SimilarCommand class extends {@link MovieIDCommand} which is here to get all Similar Movies from TheMovieDB.
 * @author Simon
 * @version 1.0
 * @since last update: 16.05.2022
 */
public class SimilarCommand extends MovieIDCommand {
    /**
     * Execute the command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/" + super.movieID +
                "/similar?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&page=1");
        return IO_Response.getResponse(super.command);
    }

    /**
     * Set the MovieID
     * @param movieID id from the Movie
     */
    @Override
    public void setMovieID(int movieID) {
        super.setMovieID(movieID);
    }
}
