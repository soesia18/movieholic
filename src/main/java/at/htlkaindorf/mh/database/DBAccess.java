package at.htlkaindorf.mh.database;

import at.htlkaindorf.mh.beans.User;
import jakarta.validation.constraints.Email;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAccess {

    private static DBAccess instance;

    private Database db;
    private Connection con;

    private final String SQL_INSERT_USER = "INSERT INTO userdata (id, email, password, rights) VALUES (DEFAULT, ?, ?, ?)";
    private final String SQL_GET_USER = "SELECT email, password, rights FROM userdata WHERE email = ?";

    private DBAccess() throws ClassNotFoundException, IOException, SQLException, Exception {
        db = Database.getInstance();
        con = db.getConnection();
    }

    public synchronized static DBAccess getInstance() throws Exception {
        if (instance == null){
            instance = new DBAccess();
        }
        return instance;
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, String.valueOf(user.getRights()));

        ps.execute();
    }

    public User findUserByEmail(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_GET_USER);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        User user = null;
        while (rs.next()) {
            user = new User(rs.getString(1), rs.getString(2), rs.getString(3).charAt(0));
        }

        return user;
    }

    public boolean validateUser(User user) throws SQLException {
        User u = findUserByEmail(user.getEmail());
        if (u.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }
}
