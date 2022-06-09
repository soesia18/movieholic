package at.htlkaindorf.mh.command;

import at.htlkaindorf.mh.database.DBAccess;
import jakarta.ws.rs.core.Response;
import lombok.Data;

@Data
public class UpdateHomePageItems extends UIDCommand {

    private boolean trending;
    private boolean nowplaying;
    private boolean toprated;
    private boolean upcoming;

    @Override
    public void setUid(String uid) {
        super.setUid(uid);
    }

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
