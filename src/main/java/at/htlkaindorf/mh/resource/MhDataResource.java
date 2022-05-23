package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.command.CommandController;
import at.htlkaindorf.mh.command.GenreCommand;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("/genres")
public class MhDataResource {
    private final GenreCommand genreCommand = new GenreCommand();
    @GET
    public Response getGenres () {
        CommandController.getInstance().setApiCommand(genreCommand);
        return CommandController.getInstance().execute();
    }


}
