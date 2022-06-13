package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.NoArgsConstructor;

/**
 * Controller class for the Command Pattern to execute the commands
 * @author Simon
 * @version 1.0
 * @since last update: 09.05.2022
 */
@NoArgsConstructor
public class CommandController {

    private static CommandController instance;


    /**
     * Create a new instance if there is no instance yet.
     * @return a {@link CommandController}
     */
    public static synchronized CommandController getInstance () {
        if (instance == null) {
            instance = new CommandController();
        }
        return instance;
    }

    /**
     * Execute the Command and get a {@link Response}
     * @param apiCommand the actual command that need to be executed
     * @return a {@link Response} from the executed {@link ApiCommand}
     */
    public synchronized Response execute(ApiCommand apiCommand) {
        return apiCommand.execute();
    }
}
