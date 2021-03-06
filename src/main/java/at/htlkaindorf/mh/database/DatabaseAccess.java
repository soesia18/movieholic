package at.htlkaindorf.mh.database;

import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Movieholic</b><br><br>
 * DatabaseAccess class to get data from the MySQL database. <br>
 * @author David
 * @version 1.0
 * @since last update: 13.06.2022
 */
public class DatabaseAccess {

    private static DatabaseAccess instance;

    private Database db;
    private Connection con;

    private final String SQL_INSERT_USER = "INSERT INTO user VALUES (?)";
    private final String SQL_INSERT_MOVIE = "INSERT INTO movie VALUES (?)";
    private final String SQL_INSERT_USERWATCHLIST = "INSERT INTO userwatchlist (uid, movieid) VALUES (?, ?)";
    private final String SQL_REMOVE_USERWATCHLIST = "DELETE FROM userwatchlist WHERE uid = ? AND movieid = ?";
    private final String SQL_GET_USERWATCHLIST = "SELECT * FROM userwatchlist WHERE uid = ?";
    private final String SQL_GET_USER = "SELECT * FROM user WHERE uid = ?";
    private final String SQL_GET_MOVIE = "SELECT * FROM movie WHERE movieid = ?";

    private final String SQL_INSERT_USERSEENLIST = "INSERT INTO userseenlist (uid, movieid) VALUES (?, ?)";
    private final String SQL_REMOVE_USERSEENLIST = "DELETE FROM userseenlist WHERE uid = ? AND movieid = ?";
    private final String SQL_GET_USERSEENLIST = "SELECT * FROM userseenlist WHERE uid = ?";

    /**
     * Returns the instance of the DatabaseAccess class, if it doesn't exist, it will be created.
     * @return {@link DatabaseAccess}
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static DatabaseAccess getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    /**
     * Private constructor to create a new DatabaseAccess object.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    private DatabaseAccess() throws ClassNotFoundException, SQLException, IOException {
        db = Database.getInstance();
        con = db.getConnection();
    }

    /**
     * Adds movie to the watchlist of given user.
     * @param {@link String} uid
     * @param {@link Integer} movieID
     * @throws Exception
     */
    public void addToWatchlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_GET_USER);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            ps = con.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, uid);
            ps.execute();
        }

        ps = con.prepareStatement(SQL_GET_MOVIE);
        ps.setInt(1, movieID);
        rs = ps.executeQuery();

        if (!rs.next()) {
            ps = con.prepareStatement(SQL_INSERT_MOVIE);
            ps.setInt(1, movieID);
            ps.execute();
        }

        ps = con.prepareStatement(SQL_INSERT_USERWATCHLIST);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

    /**
     * Removes a movie from the watchlist of a user.
     * @param {@link String} uid
     * @param {@link Integer} movieID
     * @throws Exception
     */
    public void removeFromWatchlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_REMOVE_USERWATCHLIST);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

    /**
     * Returns the watchlist of a user.
     * @param {@link String} uid
     * @return {@link List} of {@link Integer} containing the movie IDs
     * @throws Exception
     */
    public List<Integer> getWatchlistFromUser(String uid) throws SQLException {
        List<Integer> movieIDs = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement(SQL_GET_USERWATCHLIST);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            movieIDs.add(rs.getInt("movieid"));
        }

        return movieIDs;
    }

    /**
     * Adds movie to the seenlist of given user.
     * @param {@link String} uid
     * @param {@link Integer} movieID
     * @throws Exception
     */
    public void addToSeenlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_GET_USER);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            ps = con.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, uid);
            ps.execute();
        }

        ps = con.prepareStatement(SQL_GET_MOVIE);
        ps.setInt(1, movieID);
        rs = ps.executeQuery();

        if (!rs.next()) {
            ps = con.prepareStatement(SQL_INSERT_MOVIE);
            ps.setInt(1, movieID);
            ps.execute();
        }

        ps = con.prepareStatement(SQL_INSERT_USERSEENLIST);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

    /**
     * Removes a movie from the seenlist of a user.
     * @param {@link String} uid
     * @param {@link Integer} movieID
     * @throws Exception
     */
    public void removeFromSeenlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_REMOVE_USERSEENLIST);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

    /**
     * Returns the seenlist of a user.
     * @param {@link String} uid
     * @return {@link List} of {@link Integer} containing the movie IDs
     * @throws SQLException
     */
    public List<Integer> getSeenlistFromUser(String uid) throws SQLException {
        List<Integer> movieIDs = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement(SQL_GET_USERSEENLIST);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            movieIDs.add(rs.getInt("movieid"));
        }

        return movieIDs;
    }
}
