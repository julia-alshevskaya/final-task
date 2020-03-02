package com.alshevskaya.cleaningcompany.dao;

import com.alshevskaya.cleaningcompany.entity.Entity;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.PoolException;
import com.alshevskaya.cleaningcompany.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
/**
 * The {@code BaseDao} class
 * is a superclass for other DAO classes,
 * provides access to the database.
 *
 */
public abstract class BaseDao<K, T extends Entity> {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Gets all rows from the table which represents one of the entity and
     * returns them as a list of {@code Entity} objects
     *
     * @return a list contains {@code Entity}, or empty list, not null
     * @throws DaoException if a database access error occurs
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * Gets a row from the table using id,
     * builds and returns {@code Entity} object that represents this id.
     *
     * @param id a id of the entity object
     * @return an {@code Entity} object
     * @throws DaoException if a database access error occurs
     */
    public abstract T findEntity(K id) throws DaoException;

    /**
     * Delete a row from the table using id.
     *
     * @param id a id of the entity object
     * @return boolean
     * @throws DaoException if a database access error occurs
     */
    public abstract boolean delete(K id) throws DaoException;

    /**
     * Inserts a new row that represents {@code Entity} object into the table,
     * returns true if {@code Entity} was added to database,
     * sets the auto generated id to this {@code Entity} object
     *
     * @param entity an {@code Entity} object
     * @return boolean
     * @throws DaoException if a database access error occurs
     */
    public abstract boolean create(T entity) throws DaoException;

    /**
     * Update a new row that represents {@code Entity} object into the table,
     * returns entity that was update {@code Entity} and added update entity to database,
     *
     * @param entity an {@code Entity} object
     * @return an {@code Entity} object
     * @throws DaoException if a database access error occurs
     */
    public abstract T update(T entity) throws DaoException;

    public void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "ResultSet can't be closed", e);
        }
    }

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Statement can't be closed", e);
        }
    }

    /**
     * Closes the connection.
     *
     * @throws DaoException if PoolException occurs
     */
    public void close(Connection connection) throws DaoException {
        CustomConnectionPool connectionPool = CustomConnectionPool.INSTANCE;
        try {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        } catch (PoolException e) {
            throw new DaoException();
        }
    }
}
