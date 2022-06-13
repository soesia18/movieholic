package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.beans.UserMovie;
import at.htlkaindorf.mh.database.DatabaseAccess;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * <b>Movieholic</b><br><br>
 */
@Path("/seenlist")
public class MhSeenlistResource {
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addToSeenlist(UserMovie userMovie) {
        try{
            DatabaseAccess.getInstance().addToSeenlist(userMovie.getUid(), userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.ok().build();
    }

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
