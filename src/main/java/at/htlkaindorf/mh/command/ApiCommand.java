package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * Command Pattern abstract Class
 * contains a protected instance variable for a {@link URI} and a method to Execute the command
 * @author Simon
 * @version 1.0
 * @since last update: 09.05.2022
 */
public abstract class ApiCommand {

    protected URI command;

    /**
     * Will be overwritten by extended classes
     * @return {@link Response}
     */
    protected abstract Response execute();
}
