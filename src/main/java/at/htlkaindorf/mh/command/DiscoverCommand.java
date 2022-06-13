package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.*;

import java.net.URI;

/**
 * DiscoverCommand class extends {@link ApiCommand} which can be used to discover movies with the given parameters.
 * @author Simon
 * @version 1.0
 * @since last update: 23.05.2022
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

    /**
     * Execute the command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        super.command =
                URI.create("http://api.themoviedb.org/3/discover/movie?api_key=e2b8d803a857305a89319b778145cfa0" +
                        "&language=" + language + "&region="+ region +"&sort_by=" + sort_by +
                        "&include_adult=" + (includeAdult ? "true" : "false") +
                        "&year=" +  year + "&with_genres=" + with_genres + "&with_watch_monetization_types=" +
                        with_watch_monetization_types);
        return IO_Response.getResponse(super.command);
    }

    /**
     * Create a new instance of the {@link DiscoverCommandBuilder}
     * @return {@link DiscoverCommandBuilder}
     */
    public static DiscoverCommand.DiscoverCommandBuilder builder() {
        return new DiscoverCommand.DiscoverCommandBuilder();
    }

    /**
     * Builder class for the {@link DiscoverCommand}
     * @author Simon
     * @version 1.0
     * @since last update: 23.05.2022
     */
    public static class DiscoverCommandBuilder
    {
        private String language;
        private String region;
        private String sort_by;
        private String year;
        private String with_genres;
        private String with_watch_monetization_types;
        private boolean includeAdult;


        /**
         * Set the language of the command
         * @param language the language of the movie
         * @return {@link DiscoverCommandBuilder} with the added language
         */
        public DiscoverCommandBuilder language(final String language) {
            this.language = language;
            return this;
        }

        /**
         * Set the region of the command
         * @param region the region of the movie
         * @return {@link DiscoverCommandBuilder} with the added region
         */
        public DiscoverCommandBuilder region(final String region) {
            this.region = region;
            return this;
        }

        /**
         * Set the sort_by of the command
         * @param sort_by the sort_by of the movie
         * @return {@link DiscoverCommandBuilder} with the added sort_by
         */
        public DiscoverCommandBuilder sort_by(final String sort_by) {
            this.sort_by = sort_by;
            return this;
        }

        /**
         * Set the year of the command
         * @param year the year of the movie
         * @return {@link DiscoverCommandBuilder} with the added year
         */
        public DiscoverCommandBuilder year(final String year) {
            this.year = year;
            return this;
        }

        /**
         * Set the with_genres of the command
         * @param with_genres the with_genres of the movie
         * @return {@link DiscoverCommandBuilder} with the added with_genres
         */
        public DiscoverCommandBuilder with_genres(final String with_genres) {
            this.with_genres = with_genres;
            return this;
        }

        /**
         * Set the with_watch_monetization_types of the command
         * @param with_watch_monetization_types the with_watch_monetization_types of the movie
         * @return {@link DiscoverCommandBuilder} with the added with_watch_monetization_types
         */
        public DiscoverCommandBuilder with_watch_monetization_types(final String with_watch_monetization_types) {
            this.with_watch_monetization_types = with_watch_monetization_types;
            return this;
        }

        /**
         * Set the includeAdult of the command
         * @param includeAdult the includeAdult of the movie
         * @return {@link DiscoverCommandBuilder} with the added includeAdult
         */
        public DiscoverCommandBuilder includeAdult(final boolean includeAdult) {
            this.includeAdult = includeAdult;
            return this;
        }

        /**
         * Build the {@link DiscoverCommand}
         * @return {@link DiscoverCommand} with all the set parameters
         */
        public DiscoverCommand build() {
            return new DiscoverCommand(this.language, this.region, this.sort_by, this.year, this.with_genres, this.with_watch_monetization_types, this.includeAdult);
        }

    }
}
