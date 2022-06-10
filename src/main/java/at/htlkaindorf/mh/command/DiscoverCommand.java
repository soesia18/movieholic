package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.*;

import java.net.URI;

/**
 *
 */
@Getter
@ToString
@AllArgsConstructor
public class DiscoverCommand extends ApiCommand {

    private String language;
    private String region;
    private String sort_by;
    private String year;
    private String with_genres;
    private String with_watch_monetization_types;
    private boolean includeAdult;

    @Override
    protected Response execute() {
        super.command =
                URI.create("http://api.themoviedb.org/3/discover/movie?api_key=e2b8d803a857305a89319b778145cfa0" +
                        "&language=" + language + "&region="+ region +"&sort_by=" + sort_by +
                        "&include_adult=" + (includeAdult ? "true" : "false") +
                        "&year=" +  year + "&with_genres=" + with_genres + "&with_watch_monetization_types=" + with_watch_monetization_types);
        return IO_Response.getResponse(super.command);
    }

    public static DiscoverCommand.DiscoverCommandBuilder builder() {
        return new DiscoverCommand.DiscoverCommandBuilder();
    }

    public static class DiscoverCommandBuilder
    {
        private String language;
        private String region;
        private String sort_by;
        private String year;
        private String with_genres;
        private String with_watch_monetization_types;
        private boolean includeAdult;


        public DiscoverCommandBuilder language(final String language) {
            this.language = language;
            return this;
        }

        public DiscoverCommandBuilder region(final String region) {
            this.region = region;
            return this;
        }

        public DiscoverCommandBuilder sort_by(final String sort_by) {
            this.sort_by = sort_by;
            return this;
        }

        public DiscoverCommandBuilder year(final String year) {
            this.year = year;
            return this;
        }

        public DiscoverCommandBuilder with_genres(final String with_genres) {
            this.with_genres = with_genres;
            return this;
        }

        public DiscoverCommandBuilder with_watch_monetization_types(final String with_watch_monetization_types) {
            this.with_watch_monetization_types = with_watch_monetization_types;
            return this;
        }

        public DiscoverCommandBuilder includeAdult(final boolean includeAdult) {
            this.includeAdult = includeAdult;
            return this;
        }

        public DiscoverCommand build() {
            return new DiscoverCommand(this.language, this.region, this.sort_by, this.year, this.with_genres, this.with_watch_monetization_types, this.includeAdult);
        }

    }
}
