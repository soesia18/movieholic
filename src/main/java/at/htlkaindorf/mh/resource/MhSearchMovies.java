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
 * <b>API Resource</b>  <br>
 * [GET] /api/search/{searchString}/{page} - Search a Movie via String <br>
 * [GET] /api/search/{id} - Can get tmdbMovie Information <br>
 * [GET] /api/search/video/{id} - Can get the key to a Trailer <br>
 * [GET] /api/search/imdb/{id} - Can get IMDB Information <br>
 * [GET] /api/search/provider/{id} - Can get Provider Information <br>
 * [GET] /api/search/discover - Can get discover Information <br>
 * [GET] /api/search/similar/{id}
 */

@Path("/search")
public class MhSearchMovies {

    private final SearchMovieCommand searchMovieCommand = new SearchMovieCommand();
    private final TMDBMovieInformationCommand tmdbMovieInformationCommand = new TMDBMovieInformationCommand();
    private final VideoCommand videoCommand = new VideoCommand();
    private final ProviderCommand providerCommand = new ProviderCommand();
    private final IMDBInformationCommand imdbInformationCommand = new IMDBInformationCommand();
    private final DiscoverCommand discoverCommand = new DiscoverCommand();
    private final SimilarCommand similarCommand = new SimilarCommand();

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

        CommandController.getInstance().setApiCommand(searchMovieCommand);
        return CommandController.getInstance().execute();
    }

    /**
     *
     * @param movieID id from the movie
     * @return @{@link Response} with json data from a Movie<br>
     *      * If the content is ok, it will give back a json with the 200 Status <br>
     *      * If the content is not ok, it will give back no data with the 204 Status
     */
    @GET
    @Path("/{id}")
    public Response getTMDBMovieInformation(@PathParam("id") int movieID) {
        tmdbMovieInformationCommand.setMovieID(movieID);

        CommandController.getInstance().setApiCommand(tmdbMovieInformationCommand);
        return CommandController.getInstance().execute();
    }

    /**
     *
     * @param year
     * @param monetization
     * @param language
     * @param region
     * @param sort
     * @param adult
     * @param genres
     * @return
     */
    @GET
    @Path("/discover")
    public Response getDiscover (@QueryParam("year") String year, @QueryParam("monetization") String monetization,
                                 @QueryParam("language") String language, @QueryParam("region") String region,
                                 @QueryParam("sort") String sort, @QueryParam("adult") boolean adult,
                                 @QueryParam("genres") String genres) {
        discoverCommand.setYear(year);
        discoverCommand.setWith_watch_monetization_types(monetization);
        discoverCommand.setLanguage(language);
        discoverCommand.setRegion(region);
        discoverCommand.setSort_by(sort);
        discoverCommand.setIncludeAdult(adult);
        discoverCommand.setWith_genres(genres);

        CommandController.getInstance().setApiCommand(discoverCommand);
        return CommandController.getInstance().execute();
    }

    /**
     *
     * @param movieID
     * @return
     */
    @GET
    @Path("/video/{id}")
    public Response getVideo(@PathParam("id") int movieID) {
        videoCommand.setMovieID(movieID);

        CommandController.getInstance().setApiCommand(videoCommand);
        return CommandController.getInstance().execute();
    }

    /**
     *
     * @param movieID
     * @return
     */
    @GET
    @Path("/provider/{id}")
    public Response getProvider(@PathParam("id") int movieID) {
        providerCommand.setMovieID(movieID);

        CommandController.getInstance().setApiCommand(providerCommand);
        return CommandController.getInstance().execute();
    }

    /**
     *
     * @param imdbID imdb id from a movie
     * @return
     */
    @GET
    @Path("/imdb/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIMDBInformation(@PathParam("id") String imdbID) {
        imdbInformationCommand.setImdbID(imdbID);
        //http://api.themoviedb.org/3/search/movie?api_key=e2b8d803a857305a89319b778145cfa0&language=de-DE&page=1&query=saw&include_adult=false&with_genres=horror

        CommandController.getInstance().setApiCommand(imdbInformationCommand);
        return CommandController.getInstance().execute();
    }

    @GET
    @Path("/similar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimilarMovie (@PathParam("id") int movieID) {
        similarCommand.setMovieID(movieID);

        CommandController.getInstance().setApiCommand(similarCommand);
        return CommandController.getInstance().execute();
    }
}
