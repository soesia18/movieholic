package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.NoArgsConstructor;

import java.net.URI;

@NoArgsConstructor
public class SearchMovieCommand extends ApiCommand {
    private String searchString;
    private int page;

    public SearchMovieCommand (String searchString, int page) {
        this.searchString = searchString;
        this.page = page;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    protected Response execute() {
        searchString = searchString.replace(" ", "%20");
        super.command =
                URI.create("http://api.themoviedb.org/3/search/movie?api_key=e2b8d803a857305a89319b778145cfa0" +
                        "&language=de-DE&query=" + searchString + "&page=" + page);
        return IO_Response.getResponse(super.command);
    }
}
