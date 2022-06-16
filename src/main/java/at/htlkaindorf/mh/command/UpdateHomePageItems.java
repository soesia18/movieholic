package at.htlkaindorf.mh.command;

import at.htlkaindorf.mh.database.DBAccess;
import jakarta.ws.rs.core.Response;
import lombok.Data;

/**
 * <b>Movieholic</b><br><br>
 * UpdateHomePageItems class extends {@link ApiCommand} which is here to update the Home Page Items.
 * @author Simon
 * @version 1.0
 * @since last update: 09.06.2022
 */
@Data
public class UpdateHomePageItems extends UIDCommand {

    private boolean trending;
    private boolean nowplaying;
    private boolean toprated;
    private boolean upcoming;

    /**
     * Set the uid for the command
     * @param uid the uid
     */
    @Override
    public void setUid(String uid) {
        super.setUid(uid);
    }

    /**
     * Execute the command and return a {@link Response}
     * @return {@link Response} from the executed command
     */
    @Override
    protected Response execute() {
        try {
            DBAccess.getInstance().updateUserHomePageItems(uid, trending, nowplaying, toprated, upcoming);
            return Response.ok(DBAccess.getInstance().getUserHomePageItems(uid)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
