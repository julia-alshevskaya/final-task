package com.alshevskaya.cleaningcompany.dao.impl;

import com.alshevskaya.cleaningcompany.dao.BaseDao;
import com.alshevskaya.cleaningcompany.dao.ClientDao;
import com.alshevskaya.cleaningcompany.entity.Client;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.pool.CustomConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.dao.ColumnName.*;

public class ClientDaoImpl extends BaseDao<Integer, Client> implements ClientDao {

    private static final String SQL_SELECT_ALL_CLIENTS = "SELECT client_id, name, surname, phone, address FROM client";
    private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT client_id, name, surname, phone, address FROM client WHERE client_id=?";
    private static final String SQL_INSERT_CLIENT = "INSERT INTO client (name, surname, phone, address, users_login) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_CLIENT = "UPDATE client SET name=?, surname=?, phone=?, address=?  WHERE users_login=?";
    private static final String SQL_SELECT_CLIENT_BY_LOGIN = "SELECT client_id, name, surname, phone, address, users_login FROM client WHERE users_login=?";

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = new Client();
                client.setClientId(resultSet.getInt(CLIENT_CLIENT_ID));
                client.setName(resultSet.getString(CLIET_NAME));
                client.setSurname(resultSet.getString(CLIET_SURNAME));
                client.setPhone(resultSet.getString(CLIET_PHONE));
                client.setAddress(resultSet.getString(CLIET_ADDRESS));
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return clients;
    }

    @Override
    public Client findEntity(Integer id) throws DaoException {
        Client client = new Client();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            client.setClientId(resultSet.getInt(CLIENT_CLIENT_ID));
            client.setName(resultSet.getString(CLIET_NAME));
            client.setSurname(resultSet.getString(CLIET_SURNAME));
            client.setPhone(resultSet.getString(CLIET_PHONE));
            client.setAddress(resultSet.getString(CLIET_ADDRESS));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return client;
    }

    @Override
    public Client findEntityByLogin(String login) throws DaoException {
        Client client = new Client();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            client.setClientId(resultSet.getInt(CLIENT_CLIENT_ID));
            client.setName(resultSet.getString(CLIET_NAME));
            client.setSurname(resultSet.getString(CLIET_SURNAME));
            client.setPhone(resultSet.getString(CLIET_PHONE));
            client.setAddress(resultSet.getString(CLIET_ADDRESS));
            client.setLogin(resultSet.getString(CLIET_USERS_LOGIN));

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return client;
    }


    @Override
    public boolean delete(Integer id) {//TODO
        return false;
    }

    @Override
    public boolean create(Client client) throws DaoException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_CLIENT);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.setString(4, client.getAddress());
            preparedStatement.setString(5, client.getLogin());

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
    public Client update(Client clientUpdate) throws DaoException {
        Client client = findEntityByLogin(clientUpdate.getLogin());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_CLIENT);
            preparedStatement.setString(1, clientUpdate.getName());
            preparedStatement.setString(2, clientUpdate.getSurname());
            preparedStatement.setString(3, clientUpdate.getPhone());
            preparedStatement.setString(4, clientUpdate.getAddress());
            preparedStatement.setString(5, clientUpdate.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return client;
    }
}
