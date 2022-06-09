package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.command.CommandController;
import at.htlkaindorf.mh.command.GenreCommand;
import at.htlkaindorf.mh.command.UpdateHomePageItems;
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
 *
 * @author Simon
 * @version 1.0
 */
@Path("/data")
public class MhDataResource {
    private final GenreCommand genreCommand = new GenreCommand();
    private final UpdateHomePageItems updateHomePageItems = new UpdateHomePageItems();

    /**
     * To get all The Internet Movie DataBase genres
     * @return {@link Response} which contains the genres
     */
    @GET
    @Path("/genres")
    public Response getGenres() {
        return CommandController.getInstance().execute(genreCommand);
    }

    @PUT
    @Path("/homepage")
    public Response setHomePage(
            @QueryParam("uid") String uid,
            @QueryParam("trending") boolean trending,
            @QueryParam("nowplaying") boolean nowplaying,
            @QueryParam("toprated") boolean toprated,
            @QueryParam("upcoming") boolean upcoming) {

        updateHomePageItems.setUid(uid);
        updateHomePageItems.setTrending(trending);
        updateHomePageItems.setNowplaying(nowplaying);
        updateHomePageItems.setToprated(toprated);
        updateHomePageItems.setUpcoming(upcoming);

        return CommandController.getInstance().execute(updateHomePageItems);
    }

    @GET
    @Path("/homepage")
    public Response getHomePageItems(@QueryParam("uid") String uid) {

        return Response.ok().build();
    }


}
