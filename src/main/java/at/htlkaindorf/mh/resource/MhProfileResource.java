package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.beans.Profile;
import at.htlkaindorf.mh.beans.User;
import at.htlkaindorf.mh.beans.UserMovie;
import at.htlkaindorf.mh.command.IMDBInformationCommand;
import at.htlkaindorf.mh.command.TMDBMovieInformationCommand;
import at.htlkaindorf.mh.database.DatabaseAccess;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/profile")
public class MhProfileResource {

    @POST
    @Produces("application/json")
    public Response getStats(User user) {
        List<Integer> watchlist;
        List<Integer> seenlist;

        try{
            watchlist = DatabaseAccess.getInstance().getWatchlistFromUser(user.getUid());
            seenlist = DatabaseAccess.getInstance().getSeenlistFromUser(user.getUid());
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }

        Profile profile = new Profile(user.getUid(), watchlist, seenlist);
        return Response.ok(profile).build();
    }

}
