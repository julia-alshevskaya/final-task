package com.alshevskaya.cleaningcompany.dao.impl;

import com.alshevskaya.cleaningcompany.dao.BaseDao;
import com.alshevskaya.cleaningcompany.dao.OrderDao;
import com.alshevskaya.cleaningcompany.entity.Order;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.pool.CustomConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.dao.ColumnName.*;

public class OrderDaoImpl extends BaseDao<Integer, Order> implements OrderDao {
    private static final String SQL_SELECT_ALL_ORDERS = "SELECT order_id, date, price, client_client_id, staff_staff_id FROM orders";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT order_id, date, price, client_client_id, staff_staff_id FROM orders WHERE order_id=?";
    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (date, price, client_client_id, staff_staff_id) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET date=?, price=? WHERE order_id=?";
    private static final String SQL_SELECT_ORDERS_BY_CLIENT_ID = "SELECT order_id, date, price, client_client_id,staff_staff_id  FROM orders WHERE client_client_id=?";
    private static final String SQL_SELECT_ORDERS_BY_CLEANER_ID = "SELECT order_id, date, price, client_client_id,staff_staff_id  FROM orders WHERE staff_staff_id=?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM orders WHERE order_id=?";


    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt(ORDERS_ORDER_ID));
                order.setDateOrder(resultSet.getTimestamp(ORDERS_DATE).toLocalDateTime());
                order.setPrice(resultSet.getBigDecimal(ORDERS_PRICE));
                order.setClientId(resultSet.getInt(ORDERS_CLIENT_CLIENT_ID));
                order.setCleanerId(resultSet.getInt(ORDERS_STAFF_STAFF_ID));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return orders;
    }

    @Override
    public List<Order> findAllClientOrdersByClientId(Integer clientId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_CLIENT_ID);
            preparedStatement.setInt(1, clientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt(ORDERS_ORDER_ID));
                order.setDateOrder(resultSet.getTimestamp(ORDERS_DATE).toLocalDateTime());
                order.setPrice(resultSet.getBigDecimal(ORDERS_PRICE));
                order.setClientId(resultSet.getInt(ORDERS_CLIENT_CLIENT_ID));
                order.setCleanerId(resultSet.getInt(ORDERS_STAFF_STAFF_ID));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return orders;
    }

    @Override
    public List<Order> findAllClientOrdersByCleanerId(Integer cleanerId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_CLEANER_ID);
            preparedStatement.setInt(1, cleanerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt(ORDERS_ORDER_ID));
                order.setDateOrder(resultSet.getTimestamp(ORDERS_DATE).toLocalDateTime());
                order.setPrice(resultSet.getBigDecimal(ORDERS_PRICE));
                order.setClientId(resultSet.getInt(ORDERS_CLIENT_CLIENT_ID));
                order.setCleanerId(resultSet.getInt(ORDERS_STAFF_STAFF_ID));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return orders;
    }

    @Override
    public Order findEntity(Integer id) throws DaoException {
        Order order = new Order();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order.setOrderId(resultSet.getInt(ORDERS_ORDER_ID));
            order.setDateOrder(resultSet.getTimestamp(ORDERS_DATE).toLocalDateTime());
            order.setPrice(resultSet.getBigDecimal(ORDERS_PRICE));
            order.setClientId(resultSet.getInt(ORDERS_CLIENT_CLIENT_ID));
            order.setCleanerId(resultSet.getInt(ORDERS_STAFF_STAFF_ID));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public boolean create(Order order) throws DaoException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(order.getDateOrder()));
            preparedStatement.setBigDecimal(2, order.getPrice());
            preparedStatement.setInt(3, order.getClientId());
            preparedStatement.setInt(4, order.getCleanerId());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return flag;
    }

    @Override
    public Order update(Order orderUpdate) throws DaoException {
        Order order = findEntity(orderUpdate.getOrderId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(orderUpdate.getDateOrder()));
            preparedStatement.setBigDecimal(2, orderUpdate.getPrice());
            preparedStatement.setInt(3, orderUpdate.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return order;
    }
}
