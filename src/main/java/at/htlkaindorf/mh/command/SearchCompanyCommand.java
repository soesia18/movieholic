package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.Data;

import java.net.URI;

/**
 *
 */
@Data
public class SearchCompanyCommand extends ApiCommand {
    private int companyID;

    @Override
    protected Response execute() {
        super.command = URI.create("http://api.themoviedb.org/3/company/" + companyID +
                "?api_key=e2b8d803a857305a89319b778145cfa0");
        return IO_Response.getResponse(super.command);
    }
}
