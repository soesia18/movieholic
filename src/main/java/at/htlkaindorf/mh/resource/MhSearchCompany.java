package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.command.CommandController;
import at.htlkaindorf.mh.command.SearchCompanyCommand;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 * API Resource class for the search classes<br>
 * [GET] /api/searchCompany/{id} - Search a Company via id <br>
 * @author Simon
 * @version 1.0
 * @since last update: 13.06.2022
 */
@Path("/searchCompany")
public class MhSearchCompany {

    private SearchCompanyCommand searchCompanyCommand = new SearchCompanyCommand();

    /**
     * Execute the API command and return a {@link Response}
     * @param id the id of the company
     * @return {@link Response} from the executed command
     */
    @GET
    @Path("/{id}")
    public Response searchCompany (@PathParam("id") int id) {
        searchCompanyCommand.setCompanyID(id);
        return CommandController.getInstance().execute(searchCompanyCommand);
    }
}
