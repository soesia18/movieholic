package at.htlkaindorf.mh.resource;


import at.htlkaindorf.mh.command.AddFavMovieCommand;
import at.htlkaindorf.mh.command.CommandController;
import at.htlkaindorf.mh.command.RemoveFavMovieCommand;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/favorite")
public class MhFavoriteResource {
    private final AddFavMovieCommand addFavMovieCommand = new AddFavMovieCommand();

    private final RemoveFavMovieCommand removeFavMovieCommand = new RemoveFavMovieCommand();

    @POST
    @Path("/add")
    public Response addToFavorite (@QueryParam("uid") String uid, @QueryParam("movieId") int movieId) {
        addFavMovieCommand.setUid(uid);
        addFavMovieCommand.setMovieID(movieId);

        CommandController.getInstance().setApiCommand(addFavMovieCommand);
        return CommandController.getInstance().execute();
    }

    @POST
    @Path("/remove")
    public Response removeFromFavorite (@QueryParam("uid") String uid, @QueryParam("movieId") int movieId) {
        removeFavMovieCommand.setUid(uid);
        removeFavMovieCommand.setMovieID(movieId);

        CommandController.getInstance().setApiCommand(removeFavMovieCommand);
        return CommandController.getInstance().execute();
    }
}
