package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.command.CommandController;
import at.htlkaindorf.mh.command.GenreCommand;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * <b>API Resource Class</b>  <br>
 * [GET] /api/data/genres - Return a List of all genres <br>
 * [PUT] /api/data/homepage - set the HomePage item for an account <br>
 * [GET] /api/data/homepage - return the HomePage Items for an account <br>
 */
@Path("/data")
public class MhDataResource {
    private final GenreCommand genreCommand = new GenreCommand();

    /**
     * To get all The Internet Movie DataBase genres
     * @return {@link Response} which contains the genres
     */
    @GET
    @Path("/genres")
    public Response getGenres() {
        CommandController.getInstance().setApiCommand(genreCommand);
        return CommandController.getInstance().execute();
    }

    @PUT
    @Path("/homepage")
    public Response setHomePage(
            @QueryParam("uid") String uid,
            @QueryParam("trending") boolean trending,
            @QueryParam("nowplaying") boolean nowplaying,
            @QueryParam("toprated") boolean toprated,
            @QueryParam("upcoming") boolean upcoming) {


        return Response.ok().build();
    }


}
