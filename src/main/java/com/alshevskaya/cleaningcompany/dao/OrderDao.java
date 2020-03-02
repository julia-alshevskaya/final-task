package com.alshevskaya.cleaningcompany.dao;

import com.alshevskaya.cleaningcompany.entity.Order;
import com.alshevskaya.cleaningcompany.exception.DaoException;

import java.util.List;

public interface OrderDao {
    /**
     * Gets all rows from the table using id and
     * returns them as a list of {@code Order} objects
     *
     * @param clientId a id of the order object
     * @return a  list contains {@code Order} objects
     * @throws DaoException if a database access error occurs
     */
    List<Order> findAllClientOrdersByClientId(Integer clientId) throws DaoException;

    /**
     * Gets all rows from the table using id and
     * returns them as a list of {@code Order} objects
     *
     * @param cleanerId a id of the order object
     * @return a  list contains {@code Order} objects
     * @throws DaoException if a database access error occurs
     */
    List<Order> findAllClientOrdersByCleanerId(Integer cleanerId) throws DaoException;
}
