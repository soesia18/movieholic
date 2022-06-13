package at.htlkaindorf.mh.command;

/**
 * Abstract class for all API commands with uid
 * @author David
 * @version 1.0
 * @since last update: 23.05.2022
 */
public abstract class UIDCommand extends MovieIDCommand{

    String uid;

    /**
     * Set the uid
     * @param uid the uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
}
