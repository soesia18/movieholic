package at.htlkaindorf.mh.command;

import jakarta.ws.rs.core.Response;
import lombok.Data;

/**
 * <b>Movieholic</b><br><br>
 * Abstract Class for Classes that need a movie ID
 * @author Simon
 * @version 1.0
 * @since last update: 02.05.2022
 */
@Data
public abstract class MovieIDCommand extends ApiCommand {
    protected int movieID;

}
