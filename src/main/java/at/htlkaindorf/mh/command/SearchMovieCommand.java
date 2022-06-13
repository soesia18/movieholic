package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.NoArgsConstructor;

import java.net.URI;

/**
 * <b>Movieholic</b><br><br>
 * SearchMovieCommand class extends {@link MovieIDCommand} which is here to search a movie with the given searchString
 * @author Simon
 * @version 1.0
 * @since last update: 02.05.2022
 */
@NoArgsConstructor
public class SearchMovieCommand extends ApiCommand {
    private String searchString;
    private int page;

    /**
     * Constructor for the SearchMovieCommand
     * @param searchString the searchString that is used to search for a movie
     * @param page the page that is used to get the right page for the results
     */
    public SearchMovieCommand (String searchString, int page) {
        this.searchString = searchString;
        this.page = page;
    }

    /**
     * Setter for the searchString
     * @param searchString the searchString that is used to search for a movie
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Setter for the page
     * @param page the page that is used to get the right page for the results
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Execute the API command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        searchString = searchString.replace(" ", "%20");
        super.command =
                URI.create("http://api.themoviedb.org/3/search/movie?api_key=e2b8d803a857305a89319b778145cfa0" +
                        "&language=de-DE&query=" + searchString + "&page=" + page);
        return IO_Response.getResponse(super.command);
    }
}
