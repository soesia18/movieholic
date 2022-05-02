package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

public abstract class ApiCommand {

    protected URI command;
    public Response execute() {
        return Response.noContent().build();
    }
}
