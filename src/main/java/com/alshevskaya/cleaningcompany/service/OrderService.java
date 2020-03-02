package com.alshevskaya.cleaningcompany.service;

import com.alshevskaya.cleaningcompany.entity.Order;
import com.alshevskaya.cleaningcompany.exception.ServiceException;

import java.util.List;

public interface OrderService {
    /**
     * Checks if row with such id exist in database.
     *
     * @param id a order id
     * @return a {@code true} if row exists, {@code false} otherwise
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean isIdExist(String id) throws ServiceException;

    /**
     * Gets all rows from the table which represents order and
     * returns them as a list of {@code Order} objects
     *
     * @return a list contains {@code Order}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Order> findAll() throws ServiceException;

    /**
     * Gets all rows from the table which represents order using user {@code User} login and
     * returns them as a list of {@code Order} objects
     *
     * @return a list contains {@code Order}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Order> showClientsOrders(String login) throws ServiceException;

    /**
     * Gets all rows from the table which represents order using user {@code User} login and
     * returns them as a list of {@code Order} objects
     *
     * @return a list contains {@code Order}, or empty list, not null
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Order> showCleanersCompletedOrders(String login) throws ServiceException;//

    /**
     * Gets a row from the table using id,
     * builds and returns {@code Order} object that represents this id.
     *
     * @param id a id of the order object
     * @return an {@code Order} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Order findEntity(Integer id) throws ServiceException;

    /**
     * Delete a row from the table using id.
     *
     * @param id a id of the order {@code Order} object
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean delete(Integer id) throws ServiceException;

    /**
     * Inserts a new row that represents {@code Order} object into the table,
     * returns true if {@code Order} was added to database.
     *
     * @param order an {@code Order} object
     * @return boolean
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    boolean create(Order order) throws ServiceException;

    /**
     * Update a new row that represents {@code Order} user into the table,
     * returns order that was update {@code Order} and added update order to database,
     *
     * @param order an {@code Order} object
     * @return an {@code Order} object
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    Order update(Order order) throws ServiceException;

    /**
     * Gets all rows from the table using id and
     * returns them as a list of {@code Order} objects
     *
     * @param clientId a id of the order object
     * @return a  list contains {@code Order} objects
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Order> findAllClientOrdersByClientId(Integer clientId) throws ServiceException;

    /**
     * Gets all rows from the table using id and
     * returns them as a list of {@code Order} objects
     *
     * @param cleanerId a id of the order object
     * @return a  list contains {@code Order} objects
     * @throws ServiceException if {@code DaoException} occurs (database access error)
     */
    List<Order> findAllClientOrdersByCleanerId(Integer cleanerId) throws ServiceException;
}
