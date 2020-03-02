package com.alshevskaya.cleaningcompany.dao;

import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.exception.DaoException;

public interface CleanerDao {
    /**
     * Gets a row from the table using login,
     * builds and returns {@code Cleaner} object that represents this login.
     *
     * @param login a login of the user object
     * @return an {@code Cleaner} object
     * @throws DaoException if a database access error occurs
     */
    Cleaner findEntityByLogin(String login) throws DaoException;
}
