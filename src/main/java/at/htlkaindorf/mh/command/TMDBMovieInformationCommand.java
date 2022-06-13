package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 * TMDBMovieInformationCommand class extends {@link ApiCommand} which is here to get more information about a movie.
 * @author Simon
 * @version 1.0
 * @since last update: 02.05.2022
 */
@NoArgsConstructor
@Data
public class TMDBMovieInformationCommand extends MovieIDCommand {

    /**
     * Execute the API command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");
        return IO_Response.getResponse(super.command);
    }
}
