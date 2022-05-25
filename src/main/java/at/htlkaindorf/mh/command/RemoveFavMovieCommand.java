package at.htlkaindorf.mh.command;

import at.htlkaindorf.mh.database.DBAccess;
import jakarta.ws.rs.core.Response;

public class RemoveFavMovieCommand extends UIDCommand{
    @Override
    public void setMovieID(int movieID) {
        super.setMovieID(movieID);
    }

    @Override
    public void setUid(String uid) {
        super.setUid(uid);
    }

    @Override
    protected Response execute() {
        try {
            DBAccess.getInstance().removeFavoriteFromUser(uid, movieID);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
