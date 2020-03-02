package com.alshevskaya.cleaningcompany.dao;

import com.alshevskaya.cleaningcompany.entity.Client;
import com.alshevskaya.cleaningcompany.exception.DaoException;

public interface ClientDao {
    /**
     * Gets a row from the table using login,
     * builds and returns {@code Client} object that represents this login.
     *
     * @param login a login of the user object
     * @return an {@code Client} object
     * @throws DaoException if a database access error occurs
     */
    Client findEntityByLogin(String login) throws DaoException;
}
