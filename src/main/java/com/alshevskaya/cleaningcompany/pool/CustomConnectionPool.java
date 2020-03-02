package com.alshevskaya.cleaningcompany.pool;

import com.alshevskaya.cleaningcompany.exception.PoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum CustomConnectionPool {
    INSTANCE;

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;
    private final Logger logger = LogManager.getLogger();
    private Properties properties;
    private PropertyLoader propertyLoader;
    private final String PATH_CONFIG = "database.properties";
    private final static String URL = "url";
    private final int POOL_SIZE;

    CustomConnectionPool() {
        propertyLoader = new PropertyLoader();
        properties = propertyLoader.loadFiles(PATH_CONFIG);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "Failed to register driver");
            throw new RuntimeException();
        }
        POOL_SIZE = Integer.parseInt(properties.getProperty("poolSize"));
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        ProxyConnection proxyConnection = null;
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                proxyConnection = new ProxyConnection(DriverManager.getConnection(properties.getProperty(URL), properties));
                freeConnections.offer(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error initializing connection pool", e);
            }
        }
        givenConnections = new ArrayDeque<>();
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.ERROR, "Failed connection ", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws PoolException {
        if (connection.getClass() == ProxyConnection.class) {
            givenConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            throw new PoolException();
        }
    }

    public int checkPoolSize() {
        return freeConnections.size() + givenConnections.size();
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.ERROR, "Failed of close connections ", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed deregister driver ", e);
            }
        });
    }
}
