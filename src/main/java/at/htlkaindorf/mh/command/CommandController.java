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

    public synchronized Response execute(ApiCommand apiCommand) {
        return apiCommand.execute();
    }
}
