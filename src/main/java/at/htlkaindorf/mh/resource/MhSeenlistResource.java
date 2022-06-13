package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.beans.UserMovie;
import at.htlkaindorf.mh.database.DatabaseAccess;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * <b>Movieholic</b><br><br>
 * <b>API Resource Class</b>  <br>
 * [POST] /api/seenlist/add - add a movie to the seenlist of given user<br>
 * [POST] /api/seenlist/remove - remove a movie from the seenlist of given user<br>
 * [POST] /api/seenlist/get - checks if specific movie is present in seenlist of given user <br><br>
 *
 * @author David
 * @version 1.0
 * @since last update: 2022-06-11
 */
@Path("/seenlist")
public class MhSeenlistResource {
    /**
     * Adds a movie to the seenlist of a given user
     * @param @{@link UserMovie}
     * @return {@link Response}
     */
    /**
     * Adds a movie to the seenlist of a given user
     * @param {@link UserMovie}
     * @return {@link Response} which contains status code
     */
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addToSeenlist(UserMovie userMovie) {
        try{
            DatabaseAccess.getInstance().addToSeenlist(userMovie.getUid(), userMovie.getMovieID());
            DatabaseAccess.getInstance().removeFromWatchlist(userMovie.getUid(), userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.ok().build();
    }

    /**
     * Removes a movie from the seenlist of a given user
     * @param {@link UserMovie}
     * @return {@link Response} which contains status code
     */
    @POST
    @Path("/remove")
    @Consumes("application/json")
    public Response removeFromSeenlist(UserMovie userMovie){
        try{
            DatabaseAccess.getInstance().removeFromSeenlist(userMovie.getUid(), userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.ok().build();
    }

    /**
     * Checks if the seenlist from a specific user contains the given movie
     * @param {@link UserMovie}
     * @return {@link boolean} which indicates if the seenlist contains the movie
     */
    @POST
    @Path("/check")
    @Consumes("application/json")
    public boolean checkIfUserContains(UserMovie userMovie){
        try{
            return DatabaseAccess.getInstance().getSeenlistFromUser(userMovie.getUid()).contains(userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
