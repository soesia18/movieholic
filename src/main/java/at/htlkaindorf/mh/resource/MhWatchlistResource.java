package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.beans.UserMovie;
import at.htlkaindorf.mh.database.DatabaseAccess;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/watchlist")
public class MhWatchlistResource {
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addToWatchlist(UserMovie userMovie) {
        System.out.println(userMovie.getMovieID());
        try{
            DatabaseAccess.getInstance().addToWatchlist(userMovie.getUid(), userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/remove")
    @Consumes("application/json")
    public Response removeFromWatchlist(UserMovie userMovie){
        System.out.println(userMovie.getMovieID());
        try{
            DatabaseAccess.getInstance().removeFromWatchlist(userMovie.getUid(), userMovie.getMovieID());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok().build();
    }

    @POST
    @Path("/check")
    @Consumes("application/json")
    public boolean checkIfUserContains(UserMovie userMovie){
        System.out.println(userMovie.getMovieID());
        try{
            List<Integer> movieIDs = DatabaseAccess.getInstance().getWatchlistFromUser(userMovie.getUid());
            for(Integer movieID : movieIDs){
                if(movieID == userMovie.getMovieID()){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
