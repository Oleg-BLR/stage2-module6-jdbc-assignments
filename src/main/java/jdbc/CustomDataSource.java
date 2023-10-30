package jdbc;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * The CustomDataSource class implements the DataSource interface and provides a custom data source for connecting to a database.
 * It uses a singleton pattern to ensure that only one instance of the data source is created.
 */
@Getter
@Setter
public class CustomDataSource implements DataSource {
    private static volatile CustomDataSource instance;
    private static final SQLException SQL_EXCEPTION = new SQLException();
    private static final Object MONITOR = new Object();
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    /**
     * Constructs a new CustomDataSource object with the specified driver, URL, username, and password.
     * @param driver the JDBC driver class name
     * @param url the URL of the database
     * @param password the password to use when connecting to the database
     * @param name the username to use when connecting to the database
     */
    private CustomDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.password = password;
        this.name = name;
        instance = this;
    }

    /**
     * Returns the singleton instance of the CustomDataSource object.
     * If the instance has not yet been created, it will be created using the properties specified in the app.properties file.
     * @return the singleton instance of the CustomDataSource object
     * @throws RuntimeException if an IOException occurs while attempting to load the properties file
     */
    public static CustomDataSource getInstance() {
        if (instance == null) {
            synchronized (MONITOR) {
                if (instance == null) {
                    try {
                        Properties properties = new Properties();
                        properties.load(CustomDataSource.class.getClassLoader().getResourceAsStream("app.properties"));
                        instance = new CustomDataSource(
                            properties.getProperty("postgres.driver"),
                            properties.getProperty("postgres.url"),
                            properties.getProperty("postgres.password"),
                            properties.getProperty("postgres.name")
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Returns a connection to the database using the specified username and password.
     * @param username the username to use when connecting to the database
     * @param password the password to use when connecting to the database
     * @return a Connection object representing the connection to the database
     */
    @Override
    public Connection getConnection(String username, String password) {
        return new CustomConnector().getConnection(url, username, password);
    }

    /**
     * Returns a connection to the database using the default username and password.
     * @return a Connection object representing the connection to the database
     */
    @Override
    public Connection getConnection() {
        return new CustomConnector().getConnection(url, name, password);
    }

    /**
     * Throws a SQLException because this method is not supported.
     * @throws SQLException always thrown because this method is not supported
     */
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw SQL_EXCEPTION;
    }

    /**
     * Throws a SQLException because this method is not supported.
     * @throws SQLException always thrown because this method is not supported
     */
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw SQL_EXCEPTION;
    }

    /**
     * Throws a SQLException because this method is not supported.
     * @throws SQLException always thrown because this method is not supported
     */
    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw SQL_EXCEPTION;
    }

    /**
     * Throws a SQLException because this method is not supported.
     * @throws SQLException always thrown because this method is not supported
     */
    @Override
    public int getLoginTimeout() throws SQLException {
        throw SQL_EXCEPTION;
    }

    /**
     * Throws a SQLFeatureNotSupportedException because this method is not supported.
     * @throws SQLFeatureNotSupportedException always thrown because this method is not supported
     */
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

    /**
     * Throws a SQLException because this method is not supported.
     * @throws SQLException always thrown because this method is not supported
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw SQL_EXCEPTION;
    }

    /**
     * Throws a SQLException because this method is not supported.
     * @throws SQLException always thrown because this method is not supported
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw SQL_EXCEPTION;
    }
}