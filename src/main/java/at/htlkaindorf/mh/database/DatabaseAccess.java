package at.htlkaindorf.mh.database;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static DatabaseAccess getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    private DatabaseAccess() throws ClassNotFoundException, SQLException, IOException {
        db = Database.getInstance();
        con = db.getConnection();
    }

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

    public void removeFromWatchlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_REMOVE_USERWATCHLIST);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

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

    public void removeFromSeenlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_REMOVE_USERSEENLIST);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

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
