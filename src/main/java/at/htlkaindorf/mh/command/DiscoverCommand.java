package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

public class DiscoverCommand extends ApiCommand {

    private String language;
    private String region;
    private String sort_by;
    private String year;
    private String with_genres;
    private String with_watch_monetization_types;
    private boolean includeAdult;

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setWith_genres(String with_genres) {
        this.with_genres = with_genres;
    }

    public void setWith_watch_monetization_types(String with_watch_monetization_types) {
        this.with_watch_monetization_types = with_watch_monetization_types;
    }

    public void setIncludeAdult(boolean includeAdult) {
        this.includeAdult = includeAdult;
    }

    @Override
    protected Response execute() {
        super.command =
                URI.create("http://api.themoviedb.org/3/discover/movie?api_key=e2b8d803a857305a89319b778145cfa0" +
                        "&language=" + language + "&region="+ region +"&sort_by=" + sort_by +
                        "&include_adult=" + (includeAdult ? "true" : "false") +
                        "&year=" +  year + "&with_genres=" + with_genres + "&with_watch_monetization_types=" + with_watch_monetization_types);
        return IO_Response.getResponse(super.command);
    }
}
