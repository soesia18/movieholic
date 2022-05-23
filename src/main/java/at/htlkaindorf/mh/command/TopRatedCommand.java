package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

public class TopRatedCommand extends ApiCommand {
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/top_rated?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&page=1");
        return IO_Response.getResponse(super.command);
    }
}
