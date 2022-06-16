/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.mh.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * <b>Movieholic</b><br><br>
 * Database class to connect to the MySQL database. <br>
 * @author David
 * @version 1.0
 * @since last update: 10.06.2022
 */
public class Database {
    private static Database instance;
    
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;
    private String DB_DRIVER;
    
    private Connection con;
    private CachedConnection cc;

    /**
     * Returns the instance of the Database class, if it doesn't exist yet, it will be created.
     * @return {@link Database}
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static Database getInstance() throws ClassNotFoundException, SQLException, IOException{
        if(instance == null){
            instance = new Database();
        }
        
        return instance;
    }

    /**
     * Constructor to create a new Database object.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
     public Database() throws ClassNotFoundException, SQLException, IOException{
        loadProperties();
        Class.forName(DB_DRIVER);
        connect();
    }

    /**
     * Method to connect to the database.
     * @throws SQLException
     */
    private void connect() throws SQLException
    {

        con = DriverManager.getConnection(
                DB_URL,
                DB_USERNAME,
                DB_PASSWORD);
        cc = new CachedConnection(con);
    }

    /**
     * Method to load the properties from the properties file.
     * @throws IOException
     */
    private void loadProperties() throws IOException
    {
        Properties props = new Properties();


        props.load(new FileInputStream(new File(getClass().getResource("/database.properties").getFile().replace("%20", " "))));
        DB_URL = props.getProperty("DB_URL");
        DB_USERNAME = props.getProperty("DB_USERNAME");
        DB_PASSWORD = props.getProperty("DB_PASSWORD");
        DB_DRIVER = props.getProperty("DB_DRIVER");
    }

    /**
     * Method to get a statement from the database.
     * @return {@link Statement}
     * @throws Exception
     */
    public Statement getStatement() throws Exception
    {
        return cc.getStatement();
    }

    /**
     * Method to release a statement.
     * @param {@link Statement}
     * @throws Exception
     */
    public void releaseStatement(Statement stat) throws Exception
    {
        cc.releaseStatement(stat);
    }

    /**
     * Method to get the connection to the database.
     * @return {@link Connection}
     */
    public Connection getConnection() {
        return con;
    }
    
}
