package com.alshevskaya.cleaningcompany.service;

import com.alshevskaya.cleaningcompany.entity.Service;
import com.alshevskaya.cleaningcompany.exception.ServiceException;

import java.util.List;

public interface CleaningService {
    /**
     * Gets all rows from the table which represents service and
     * returns them as a list of {@code Service} objects
     *
     * @return a list contains {@code Service}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Service> findAll() throws ServiceException;

    /**
     * Checks if row with such id exist in database.
     *
     * @param id a order id
     * @return a {@code true} if row exists, {@code false} otherwise
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean isIdExist(String id) throws ServiceException;

    /**
     * Gets a row from the table using id,
     * builds and returns {@code Service} object that represents this id.
     *
     * @param id a id of the order object
     * @return an {@code Service} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Service findEntity(Integer id) throws ServiceException;

    /**
     * Delete a row from the table using id.
     *
     * @param id a id of the order {@code Service} object
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean delete(Integer id) throws ServiceException;

    /**
     * Inserts a new row that represents {@code Service} object into the table,
     * returns true if {@code Service} was added to database.
     *
     * @param service an {@code Service} object
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean create(Service service) throws ServiceException;

    /**
     * Update a new row that represents {@code Service} service into the table,
     * returns order that was update {@code Service} and added update service to database,
     *
     * @param service an {@code Service} object
     * @return an {@code Service} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Service update(Service service) throws ServiceException;
}
