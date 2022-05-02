package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

public abstract class MovieIDCommand extends ApiCommand {
    int movieID;

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    @Override
    public Response execute() {
        return super.execute();
    }
}
