package at.htlkaindorf.mh.database;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private static DatabaseAccess instance;

    private Database db;
    private Connection con;

    private final String SQL_INSERT_WATCHLIST = "INSERT INTO watchlist VALUES (?)";
    private final String SQL_INSERT_MOVIE = "INSERT INTO movie VALUES (?)";
    private final String SQL_INSERT_WATCHLISTMOVIE = "INSERT INTO watchlistmovie (uid, movieid) VALUES (?, ?)";
    private final String SQL_REMOVE_WATCHLISTMOVIE = "DELETE FROM watchlistmovie WHERE uid = ? AND movieid = ?";
    private final String SQL_GET_WATCHLISTMOVIE = "SELECT * FROM watchlistmovie WHERE uid = ?";
    private final String SQL_GET_WATCHLIST = "SELECT * FROM watchlist WHERE uid = ?";
    private final String SQL_GET_MOVIE = "SELECT * FROM movie WHERE movieid = ?";

    public static DatabaseAccess getInstance() throws SQLException, IOException, ClassNotFoundException {
        if (instance == null){
            instance = new DatabaseAccess();
        }
        return instance;
    }

    private DatabaseAccess() throws ClassNotFoundException, SQLException, IOException {
        db = Database.getInstance();
        con = db.getConnection();
    }

    public void addToWatchlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_GET_WATCHLIST);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();

        if(!rs.next()){
            ps = con.prepareStatement(SQL_INSERT_WATCHLIST);
            ps.setString(1, uid);
            ps.execute();
        }

        ps = con.prepareStatement(SQL_GET_MOVIE);
        ps.setInt(1, movieID);
        rs = ps.executeQuery();

        if(!rs.next()){
            ps = con.prepareStatement(SQL_INSERT_MOVIE);
            ps.setInt(1, movieID);
            ps.execute();
        }

        ps = con.prepareStatement(SQL_INSERT_WATCHLISTMOVIE);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

    public void removeFromWatchlist(String uid, int movieID) throws Exception {
        PreparedStatement ps = con.prepareStatement(SQL_REMOVE_WATCHLISTMOVIE);
        ps.setString(1, uid);
        ps.setInt(2, movieID);
        ps.execute();
    }

    public List<Integer> getWatchlistFromUser(String uid) throws SQLException {
        List<Integer> movieIDs = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement(SQL_GET_WATCHLISTMOVIE);
        ps.setString(1, uid);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            movieIDs.add(rs.getInt("movieid"));
        }

        return movieIDs;
    }

    public static void main(String[] args) {
        try {
            DatabaseAccess db = DatabaseAccess.getInstance();
            db.addToWatchlist("test2", 13);
            db.addToWatchlist("test2", 14);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
