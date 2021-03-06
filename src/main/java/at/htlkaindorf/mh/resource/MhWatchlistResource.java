package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.beans.UserMovie;
import at.htlkaindorf.mh.database.DatabaseAccess;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Movieholic</b><br><br>
 * <b>API Resource Class</b>  <br>
 * [POST] /api/watchlist/add - add a movie to the watchlist of given user <br>
 * [POST] /api/watchlist/remove - remove a movie from the watchlist of given user<br>
 * [POST] /api/watchlist/get - checks if specific movie is present in watchlist of given user <br>
 * @author David
 * @version 1.0
 * @since last update: 11.06.2022
 */
@Path("/watchlist")
public class MhWatchlistResource {
    /**
     * Adds a movie to the watchlist of a given user
     * @param {@link UserMovie}
     * @return {@link Response} which contains status code
     */
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addToWatchlist(UserMovie userMovie) {
        try{
            DatabaseAccess.getInstance().addToWatchlist(userMovie.getUid(), userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.ok().build();
    }

    /**
     * Removes a movie from the watchlist of a given user
     * @param {@link UserMovie}
     * @return {@link Response} which contains status code
     */
    @POST
    @Path("/remove")
    @Consumes("application/json")
    public Response removeFromWatchlist(UserMovie userMovie){
        try{
            DatabaseAccess.getInstance().removeFromWatchlist(userMovie.getUid(), userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.ok().build();
    }

    /**
     * Checks if the watchlist from a specific user contains the given movie
     * @param {@link UserMovie}
     * @return {@link boolean} which indicates if the watchlist contains the movie
     */
    @POST
    @Path("/check")
    @Consumes("application/json")
    public boolean checkIfUserContains(UserMovie userMovie){
        try{
            return DatabaseAccess.getInstance().getWatchlistFromUser(userMovie.getUid()).contains(userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
