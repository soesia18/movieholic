package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.NoArgsConstructor;

import java.net.URI;

@NoArgsConstructor
public class TMDBMovieInformationCommand extends MovieIDCommand {

    @Override
    public void setMovieID(int movieID) {
        super.setMovieID(movieID);
    }

    @Override
    public Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");
        return IO_Response.getResponse(super.command);
    }
}
