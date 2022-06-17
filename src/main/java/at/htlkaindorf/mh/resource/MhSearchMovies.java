package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.command.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * <b>Movieholic</b><br><br>
 * <b>API Resource Class</b>  <br>
 * [GET] /api/search/{searchString}/{page} - Search a Movie via String <br>
 * [GET] /api/search/{id} - Can get tmdbMovie Information <br>
 * [GET] /api/search/video/{id} - Can get the key to a Trailer <br>
 * [GET] /api/search/imdb/{id} - Can get IMDB Information <br>
 * [GET] /api/search/provider/{id} - Can get Provider Information <br>
 * [GET] /api/search/discover - Can get discover Information <br>
 * [GET] /api/search/similar/{id} - Get all similar Movies from a movie id <br>
 * [GET] /api/search/upcoming - Get all Upcoming Movie <br>
 * [GET] /api/search/nowplaying - Get all Nowplaying Movie <br>
 * [GET] /api/search/toprated - Get all Toprated Movie <br>
 * @author Simon
 * @version 1.0
 * @since last update: 16.06.2022
 */

@Path("/search")
public class MhSearchMovies {

    private final SearchMovieCommand searchMovieCommand = new SearchMovieCommand();
    private final TMDBMovieInformationCommand tmdbMovieInformationCommand = new TMDBMovieInformationCommand();
    private final VideoCommand videoCommand = new VideoCommand();
    private final ProviderCommand providerCommand = new ProviderCommand();
    private final IMDBInformationCommand imdbInformationCommand = new IMDBInformationCommand();
    private final SimilarCommand similarCommand = new SimilarCommand();
    private final NowPlayingCommand nowPlayingCommand = new NowPlayingCommand();
    private final TopRatedCommand topRatedCommand = new TopRatedCommand();
    private final UpcomingCommand upcomingCommand = new UpcomingCommand();

    private DiscoverCommand discoverCommand;

    public MhSearchMovies() {

    }

    /**
     * @param searchString {@link String} for searching (e.g. "Shrek")
     * @param page         {@link Integer}, which page will return (e.g. 1)
     * @return {@link Response} which contains the data <br>
     * If the content is ok, it will give back a json with the 200 Status <br>
     * If the content is not ok, it will give back no data with the 204 Status
     */
    @GET
    @Path("/{searchString}/{page}")
    public Response searchMovie(@PathParam("searchString") String searchString, @PathParam("page") int page) {
        searchMovieCommand.setSearchString(searchString);
        searchMovieCommand.setPage(page);

        return CommandController.getInstance().execute(searchMovieCommand);
    }

    /**
     * @param movieID id from the movie
     * @return @{@link Response} with json data from a Movie<br>
     * * If the content is ok, it will give back a json with the 200 Status <br>
     * * If the content is not ok, it will give back no data with the 204 Status
     */
    @GET
    @Path("/{id}")
    public Response getTMDBMovieInformation(@PathParam("id") int movieID) {
        tmdbMovieInformationCommand.setMovieID(movieID);

        return CommandController.getInstance().execute(tmdbMovieInformationCommand);
    }

    /**
     * Discover the IMDB for movies that containts the specified parameters
     * @param year {@link Integer} for the year of the movie
     * @param monetization {@link String} for the monetization of the movie
     * @param language {@link String} for the language of the movie
     * @param region {@link String} for the region of the movie
     * @param sort {@link String} for the sort of the movie
     * @param adult {@link String} for the adult of the movie
     * @param genres {@link String} for the genres of the movie
     * @return @{@link Response} with json data from Movies<br>
     */
    @GET
    @Path("/discover")
    public Response getDiscover(@QueryParam("year") String year, @QueryParam("monetization") String monetization,
                                @QueryParam("language") String language, @QueryParam("region") String region,
                                @QueryParam("sort") String sort, @QueryParam("adult") boolean adult,
                                @QueryParam("genres") String genres) {

        DiscoverCommand.DiscoverCommandBuilder builder = DiscoverCommand.builder();

        /*discoverCommand =
                DiscoverCommand
                        .builder()
                        .year(year)
                        .with_watch_monetization_types(monetization)
                        .language(language)
                        .sort_by(sort)
                        .region(region)
                        .with_genres(genres)
                        .includeAdult(adult)
                        .build();*/

        if (!year.isBlank()) builder.year(year);
        if (!monetization.isBlank()) builder.with_watch_monetization_types(monetization);
        if (!language.isBlank()) builder.language(language);
        if (!region.isBlank()) builder.region(region);
        if (!sort.isBlank()) builder.sort_by(sort);

        builder
                .includeAdult(adult)
                .with_genres(genres);

        discoverCommand = builder.build();
        return CommandController.getInstance().execute(discoverCommand);
    }

    /**
     * Gets a Response for the Trailer Video
     * @param movieID {@link Integer} for the id of the movie
     * @return @{@link Response} with json data from a Movie<br>
     */
    @GET
    @Path("/video/{id}")
    public Response getVideo(@PathParam("id") int movieID) {
        videoCommand.setMovieID(movieID);

        return CommandController.getInstance().execute(videoCommand);
    }

    /**
     * Gets a Response for the Provider Information
     * @param movieID {@link Integer} for the id of the movie
     * @return @{@link Response} with json data from a Movie<br>
     */
    @GET
    @Path("/provider/{id}")
    public Response getProvider(@PathParam("id") int movieID) {
        providerCommand.setMovieID(movieID);

        return CommandController.getInstance().execute(providerCommand);
    }

    /**
     * Gets a Response for the IMDB Information
     * @param imdbID imdb id from a movie
     * @return @{@link Response} with json data from a Movie<br>
     */
    @GET
    @Path("/imdb/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIMDBInformation(@PathParam("id") String imdbID) {
        imdbInformationCommand.setImdbID(imdbID);
        //http://api.themoviedb.org/3/search/movie?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE&page=1&query=saw&include_adult=false&with_genres=horror

        return CommandController.getInstance().execute(imdbInformationCommand);
    }

    /**
     * Gets a Response for the Similar Movies
     * @param movieID {@link Integer} for the id of the movie
     * @return @{@link Response} with json data from Movies<br>
     */
    @GET
    @Path("/similar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimilarMovie(@PathParam("id") int movieID) {
        similarCommand.setMovieID(movieID);

        return CommandController.getInstance().execute(similarCommand);
    }

    /**
     * Gets a Response for the Now Playing Movies
     * @return @{@link Response} with json data from Movies<br>
     */
    @GET
    @Path("/nowplaying")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNowPlaying() {
        return CommandController.getInstance().execute(nowPlayingCommand);
    }

    /**
     * Gets a Response for the Upcoming Movies
     * @return @{@link Response} with json data from Movies<br>
     */
    @GET
    @Path("/toprated")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopRated() {
        return CommandController.getInstance().execute(topRatedCommand);
    }

    /**
     * Gets a Response for the Popular Movies
     * @return @{@link Response} with json data from Movies<br>
     */
    @GET
    @Path("/upcoming")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUpcoming() {
        return CommandController.getInstance().execute(upcomingCommand);
    }
}
