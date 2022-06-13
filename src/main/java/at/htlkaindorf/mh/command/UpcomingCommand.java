package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 */
public class UpcomingCommand extends ApiCommand {
    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/movie/upcoming?api_key=e2b8d803a857305a89319b778145cfa0&language=en-US&page=1");
        return IO_Response.getResponse(super.command);
    }
}
