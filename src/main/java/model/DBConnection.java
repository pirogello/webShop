package model;

import org.postgresql.core.QueryExecutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private Connection connection;
    private String driver;
    private String url;
    private String user;
    private String pass;


    public DBConnection() {
        Properties properties = new Properties();
        try {

            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "db.properties";;
            properties.load(new FileInputStream(appConfigPath));

            driver = properties.getProperty("db.driverClassName");
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
