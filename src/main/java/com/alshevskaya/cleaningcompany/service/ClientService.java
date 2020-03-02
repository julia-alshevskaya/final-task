package com.alshevskaya.cleaningcompany.service;

import com.alshevskaya.cleaningcompany.entity.Client;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;

import java.util.List;

public interface ClientService {
    /**
     * Inserts a new row that represents {@code User} and {@code Client}  object into the tables,
     * returns true if {@code User} and {@code Client} was added to database.
     *
     * @param login, password, name, surname, phone, address {@code User} and {@code Client} objects
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean addClient(String login, String password, String name, String surname,
                      String phone, String address) throws ServiceException;

    /**
     * Checks if row with such login exist in database.
     *
     * @param enterLogin a user login
     * @return a {@code true} if row exists, {@code false} otherwise
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean isLoginExist(String enterLogin) throws ServiceException;

    /**
     * Gets all rows from the table which represents client and
     * returns them as a list of {@code Client} objects
     *
     * @return a list contains {@code Client}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Client> findAll() throws ServiceException;

    /**
     * Gets a row from the table using id,
     * builds and returns {@code Client} object that represents this id.
     *
     * @param id a id of the client object
     * @return an {@code Client} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Client findEntity(Integer id) throws ServiceException;

    /**
     * Gets a row from the table using login,
     * builds and returns {@code Client} object that represents this login.
     *
     * @param login a login of the client object
     * @return an {@code Client} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Client findEntityByLogin(String login) throws ServiceException;

    /**
     * Inserts a new row that represents {@code Client} object into the table,
     * returns true if {@code Client} was added to database.
     *
     * @param client an {@code Client} object
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean create(Client client) throws DaoException, ServiceException;

    /**
     * Update a new row that represents {@code Client} client into the table,
     * returns client that was update {@code Client} and added update client to database,
     *
     * @param client an {@code Client} object
     * @return an {@code Client} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Client update(Client client) throws DaoException, ServiceException;
}
