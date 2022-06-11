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
 *
 * @author David
 */
public class Database {
    private static Database instance;
    
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;
    private String DB_DRIVER;
    
    private Connection con;
    private CachedConnection cc;
    
    public static Database getInstance() throws ClassNotFoundException, SQLException, IOException{
        if(instance == null){
            instance = new Database();
        }
        
        return instance;
    }
    
     public Database() throws ClassNotFoundException, SQLException, IOException{
        loadProperties();
        Class.forName(DB_DRIVER);
        connect();
    }
    
    private void connect() throws SQLException
    {

        con = DriverManager.getConnection(
                DB_URL,
                DB_USERNAME,
                DB_PASSWORD);
        cc = new CachedConnection(con);
    }

    private void loadProperties() throws IOException
    {
        Properties props = new Properties();


        props.load(new FileInputStream(new File(getClass().getResource("/database.properties").getFile().replace("%20", " "))));
        DB_URL = props.getProperty("DB_URL");
        DB_USERNAME = props.getProperty("DB_USERNAME");
        DB_PASSWORD = props.getProperty("DB_PASSWORD");
        DB_DRIVER = props.getProperty("DB_DRIVER");
    }
    
    public Statement getStatement() throws Exception
    {
        return cc.getStatement();
    }
    
    public void releaseStatement(Statement stat) throws Exception
    {
        cc.releaseStatement(stat);
    }
    
    public Connection getConnection() {
        return con;
    }
    
}
