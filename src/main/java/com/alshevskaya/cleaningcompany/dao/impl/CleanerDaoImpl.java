package com.alshevskaya.cleaningcompany.dao.impl;

import com.alshevskaya.cleaningcompany.dao.BaseDao;
import com.alshevskaya.cleaningcompany.dao.CleanerDao;
import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.pool.CustomConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.dao.ColumnName.*;

public class CleanerDaoImpl extends BaseDao<Integer, Cleaner> implements CleanerDao {
    private static final String SQL_SELECT_ALL_CLEANERS = "SELECT staff_id, name, surname, phone, address FROM staff";
    private static final String SQL_SELECT_CLEANER_BY_ID = "SELECT staff_id, name, surname, phone, address FROM staff WHERE staff_id=?";
    private static final String SQL_INSERT_CLEANER = "INSERT INTO staff (name, surname, phone, address, users_login) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_CLEANER = "UPDATE staff SET name=?, surname=?, phone=?, address=?  WHERE users_login=?";
    private static final String SQL_SELECT_CLEANER_BY_LOGIN = "SELECT staff_id, name, surname, phone, address, users_login FROM staff WHERE users_login=?";

    @Override
    public List<Cleaner> findAll() throws DaoException {
        List<Cleaner> cleaners = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_CLEANERS);
            while (resultSet.next()) {
                Cleaner cleaner = new Cleaner();
                cleaner.setCleanerId(resultSet.getInt(CLEANER_ID));
                cleaner.setName(resultSet.getString(CLEANER_NAME));
                cleaner.setSurname(resultSet.getString(CLEANER_SURNAME));
                cleaner.setPhone(resultSet.getString(CLEANER_PHONE));
                cleaner.setAddress(resultSet.getString(CLEANER_ADDRESS));
                cleaners.add(cleaner);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return cleaners;
    }

    @Override
    public Cleaner findEntity(Integer id) throws DaoException {
        Cleaner cleaner = new Cleaner();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CLEANER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cleaner.setCleanerId(resultSet.getInt(CLEANER_ID));
            cleaner.setName(resultSet.getString(CLEANER_NAME));
            cleaner.setSurname(resultSet.getString(CLEANER_SURNAME));
            cleaner.setPhone(resultSet.getString(CLEANER_PHONE));
            cleaner.setAddress(resultSet.getString(CLEANER_ADDRESS));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return cleaner;
    }

    @Override
    public Cleaner findEntityByLogin(String login) throws DaoException {
        Cleaner cleaner = new Cleaner();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CLEANER_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cleaner.setCleanerId(resultSet.getInt(CLEANER_ID));
            cleaner.setName(resultSet.getString(CLEANER_NAME));
            cleaner.setSurname(resultSet.getString(CLEANER_SURNAME));
            cleaner.setPhone(resultSet.getString(CLEANER_PHONE));
            cleaner.setAddress(resultSet.getString(CLEANER_ADDRESS));
            cleaner.setLogin(resultSet.getString(CLEANER_USERS_LOGIN));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return cleaner;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Cleaner cleaner) throws DaoException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_CLEANER);
            preparedStatement.setString(1, cleaner.getName());
            preparedStatement.setString(2, cleaner.getSurname());
            preparedStatement.setString(3, cleaner.getPhone());
            preparedStatement.setString(4, cleaner.getAddress());
            preparedStatement.setString(5, cleaner.getLogin());
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
    public Cleaner update(Cleaner cleanerUpdate) throws DaoException {
        Cleaner cleaner = findEntityByLogin(cleanerUpdate.getLogin());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_CLEANER);
            preparedStatement.setString(1, cleanerUpdate.getName());
            preparedStatement.setString(2, cleanerUpdate.getSurname());
            preparedStatement.setString(3, cleanerUpdate.getPhone());
            preparedStatement.setString(4, cleanerUpdate.getAddress());
            preparedStatement.setString(5, cleanerUpdate.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return cleaner;
    }
}
