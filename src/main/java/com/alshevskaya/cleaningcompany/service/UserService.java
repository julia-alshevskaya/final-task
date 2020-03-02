package com.alshevskaya.cleaningcompany.service;

import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;

import java.util.List;

public interface UserService {
    /**
     * Checks which row has user with such login in database and return it.
     *
     * @param enterLogin    a user login
     * @return a {@code  UserRole} of user
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    UserRole checkUserRole(String enterLogin) throws ServiceException;
    /**
     * Checks if row with login and password exists in database.
     *
     * @param enterLogin    a user login
     * @param enterPassword a user password
     * @return a {@code true} if row exists, {@code false} otherwise
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean checkLogin(String enterLogin, String enterPassword) throws ServiceException;
    /**
     * Checks if row with such login exist in database.
     *
     * @param enterLogin    a user login
     * @return a {@code true} if row exists, {@code false} otherwise
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean isLoginExist(String enterLogin) throws ServiceException;

    /**
     * Gets all rows from the table which represents user and
     * returns them as a list of {@code User} objects
     *
     * @return a list contains {@code User}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<User> findAllUsers() throws ServiceException;
    /**
     * Gets a row from the table using login,
     * builds and returns {@code User} object that represents this login.
     *
     * @param login a login of the user object
     * @return an {@code User} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    User findByLogin(String login) throws ServiceException;
    /**
     * Inserts a new row that represents {@code User} object into the table,
     * returns true if {@code User} was added to database.
     *
     * @param user an {@code User} object
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean addUser(User user) throws DaoException, ServiceException;
    /**
     * Update a new row that represents {@code User} user into the table,
     * returns user that was update {@code User} and added update user to database,
     *
     * @param user an {@code User} object
     * @return an {@code User} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    User update(User user) throws DaoException, ServiceException;
    /**
     * Update a new row that represents {@code User} object into the table,
     * returns entity that was update {@code User} and added update entity to database,
     *
     * @param user an {@code User} object
     * @return an {@code User} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    User updatePasswordByLogin(User user) throws ServiceException;
    /**
     * Gets all rows from the table which represents one of the role and
     * returns them as a list of {@code User} objects
     *
     * @return a list contains {@code User}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<User> findByRole(UserRole role) throws ServiceException;
}
