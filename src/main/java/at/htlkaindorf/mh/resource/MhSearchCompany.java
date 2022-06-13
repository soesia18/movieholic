package at.htlkaindorf.mh.resource;

import at.htlkaindorf.mh.command.CommandController;
import at.htlkaindorf.mh.command.SearchCompanyCommand;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 *
 */
@Path("/searchCompany")
public class MhSearchCompany {

    private SearchCompanyCommand searchCompanyCommand = new SearchCompanyCommand();

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    public Response searchCompany (@PathParam("id") int id) {
        searchCompanyCommand.setCompanyID(id);
        return CommandController.getInstance().execute(searchCompanyCommand);
    }
}
