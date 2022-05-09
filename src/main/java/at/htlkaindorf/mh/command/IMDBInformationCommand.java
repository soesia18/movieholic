package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

public class IMDBInformationCommand extends ApiCommand {

    private String imdbID;

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @Override
    protected Response execute() {
        super.command = URI.create("https://imdb-api.com/de/API/Title/k_gjl52lcn/" + imdbID + "/FullActor,FullCast,Posters,Images,Trailer,Ratings,Wikipedia,");
        return IO_Response.getResponse(super.command);
    }
}
