package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

public class ProviderCommand extends MovieIDCommand {
    @Override
    public void setMovieID(int movieID) {
        super.setMovieID(movieID);
    }

    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/" + movieID + "/watch/providers?api_key=e2b8d803a857305a89319b778145cfa0");
        return IO_Response.getResponse(super.command);
    }
}
