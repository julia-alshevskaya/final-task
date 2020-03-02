package com.alshevskaya.cleaningcompany.service;

import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.exception.ServiceException;

import java.util.List;

public interface CleanerService {
    /**
     * Inserts a new row that represents {@code User} and {@code Cleaner}  object into the tables,
     * returns true if {@code User} and {@code Cleaner} was added to database.
     *
     * @param login, password, role, name, surname, phone, address {@code User} and {@code Cleaner} objects
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean addCleaner(String login, String password, String role, String name, String surname,
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
     * Gets all rows from the table which represents cleaner and
     * returns them as a list of {@code Cleaner} objects
     *
     * @return a list contains {@code Cleaner}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Cleaner> findAll() throws ServiceException;

    /**
     * Gets a row from the table using id,
     * builds and returns {@code Cleaner} object that represents this id.
     *
     * @param id a id of the cleaner object
     * @return an {@code Cleaner} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Cleaner findEntity(Integer id) throws ServiceException;

    /**
     * Gets a row from the table using login,
     * builds and returns {@code Cleaner} object that represents this login.
     *
     * @param login a login of the cleaner object
     * @return an {@code Cleaner} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Cleaner findEntityByLogin(String login) throws ServiceException;

    /**
     * Inserts a new row that represents {@code Cleaner} object into the table,
     * returns true if {@code Cleaner} was added to database.
     *
     * @param cleaner an {@code Cleaner} object
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean create(Cleaner cleaner) throws ServiceException;

    /**
     * Update a new row that represents {@code Cleaner} cleaner into the table,
     * returns cleaner that was update {@code Cleaner} and added update cleaner to database,
     *
     * @param cleaner an {@code Cleaner} object
     * @return an {@code Cleaner} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Cleaner update(Cleaner cleaner) throws ServiceException;
}
