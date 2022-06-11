package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * IMDBInformationCommand class extends {@link ApiCommand} which is here to get all detailed information from IMDB.
 * @author Simon
 * @version 1.0
 * @since last update: 02.05.2022
 */
public class IMDBInformationCommand extends ApiCommand {

    private String imdbID;

    /**
     * setter for the imdbID
     * @param imdbID the id for the imdb movie
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     * Execute the command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command = URI.create("https://imdb-api.com/de/API/Title/k_gjl52lcn/" + imdbID + "/FullActor,FullCast,Posters,Images,Trailer,Ratings,Wikipedia,");
        return IO_Response.getResponse(super.command);
    }
}
