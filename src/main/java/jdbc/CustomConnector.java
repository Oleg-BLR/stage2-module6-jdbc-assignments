package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The CustomConnector class provides methods to establish a connection to a database.
 * It uses the DriverManager class to create a connection using the provided URL, username, and password.
 */
public class CustomConnector {

    /**
     * Returns a connection to the database using the provided URL.
     * @param url the URL of the database
     * @return a Connection object representing the connection to the database
     * @throws RuntimeException if a SQLException occurs while attempting to establish the connection
     */
    public Connection getConnection(String url) {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a connection to the database using the provided URL, username, and password.
     * @param url the URL of the database
     * @param user the username to use when connecting to the database
     * @param password the password to use when connecting to the database
     * @return a Connection object representing the connection to the database
     * @throws RuntimeException if a SQLException occurs while attempting to establish the connection
     */
    public Connection getConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}