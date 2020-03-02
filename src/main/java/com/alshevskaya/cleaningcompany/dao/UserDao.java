package com.alshevskaya.cleaningcompany.dao;

import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.DaoException;

import java.util.List;

public interface UserDao {
    /**
     * Gets all rows from the table which represents one of the role and
     * returns them as a list of {@code User} objects
     *
     * @return a list contains {@code User}, or empty list, not null
     * @throws DaoException if a database access error occurs
     */
    List<User> findByRole(UserRole role) throws DaoException;

    /**
     * Update a new row that represents {@code User} object into the table,
     * returns entity that was update {@code User} and added update entity to database,
     *
     * @param user an {@code User} object
     * @return an {@code User} object
     * @throws DaoException if a database access error occurs
     */
    User updatePasswordByLogin(User user) throws DaoException;
}
