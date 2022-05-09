package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

public class CommandController {

    private static CommandController instance;

    private CommandController () {

    }

    public static synchronized CommandController getInstance () {
        if (instance == null) {
            instance = new CommandController();
        }
        return instance;
    }

    private ApiCommand apiCommand;

    public void setApiCommand(ApiCommand apiCommand) {
        this.apiCommand = apiCommand;
    }

    public Response execute() {
        return apiCommand.execute();
    }
}