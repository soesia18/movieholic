package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 */
public class VideoCommand extends MovieIDCommand {

    @Override
    public void setMovieID(int movieID) {
        super.setMovieID(movieID);
    }

    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "/videos?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE");
        return IO_Response.getResponse(super.command);
    }
}
