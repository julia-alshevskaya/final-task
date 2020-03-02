package com.alshevskaya.cleaningcompany.dao.impl;

import com.alshevskaya.cleaningcompany.dao.BaseDao;
import com.alshevskaya.cleaningcompany.dao.ServiceDao;
import com.alshevskaya.cleaningcompany.entity.Service;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.pool.CustomConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.dao.ColumnName.*;

public class ServiceDaoImpl extends BaseDao<Integer, Service> implements ServiceDao {
    private static final String SQL_SELECT_ALL_SERVICES = "SELECT service_id, service_name, price_per_item, order_quantity FROM services";
    private static final String SQL_SELECT_SERVICE_BY_ID = "SELECT service_id, service_name, price_per_item, order_quantity FROM services WHERE service_id=?";
    private static final String SQL_INSERT_SERVICE = "INSERT INTO services (service_name, price_per_item, order_quantity) VALUES (?,?,?)";
    private static final String SQL_UPDATE_SERVICE = "UPDATE services SET service_name=?, price_per_item=?, order_quantity=? WHERE service_id=?";

    private static final String SQL_DELETE_BY_ID = "DELETE FROM services WHERE service_id=?";

    @Override
    public List<Service> findAll() throws DaoException {
        List<Service> services = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_SERVICES);
            while (resultSet.next()) {
                Service service = new Service();
                service.setServiceId(resultSet.getInt(SERVICES_SERVICE_ID));
                service.setServiceName(resultSet.getString(SERVICES_SERVICE_NAME));
                service.setPriceItem(resultSet.getBigDecimal(SERVICES_PRICE));
                service.setOrderQuantity(resultSet.getDouble(SERVICES_ORDER_QUANTITY));
                services.add(service);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return services;
    }

    @Override
    public Service findEntity(Integer id) throws DaoException {
        Service service = new Service();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_SERVICE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            service.setServiceId(resultSet.getInt(SERVICES_SERVICE_ID));
            service.setServiceName(resultSet.getString(SERVICES_SERVICE_NAME));
            service.setPriceItem(resultSet.getBigDecimal(SERVICES_PRICE));
            service.setOrderQuantity(resultSet.getDouble(SERVICES_ORDER_QUANTITY));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return service;
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
    public boolean create(Service service) throws DaoException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_SERVICE);
            preparedStatement.setString(1,service.getServiceName());
            preparedStatement.setBigDecimal(2, service.getPriceItem());
            preparedStatement.setDouble(3, service.getOrderQuantity());
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
    public Service update(Service serviceUpdate) throws DaoException {
        Service service = findEntity(serviceUpdate.getServiceId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_SERVICE);
            preparedStatement.setString(1,serviceUpdate.getServiceName());
            preparedStatement.setBigDecimal(2, serviceUpdate.getPriceItem());
            preparedStatement.setDouble(3,serviceUpdate.getOrderQuantity());
            preparedStatement.setInt(4, serviceUpdate.getServiceId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return service;
    }
}
