package com.alshevskaya.cleaningcompany.dao.impl;

import com.alshevskaya.cleaningcompany.dao.BaseDao;
import com.alshevskaya.cleaningcompany.dao.UserDao;
import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.pool.CustomConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.dao.ColumnName.*;

public class UserDaoImpl extends BaseDao<String, User> implements UserDao {
    private static final String SQL_SELECT_ALL_USERS = "SELECT login, password, roles_role_name FROM users";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT login, password, roles_role_name FROM users WHERE login=?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE login=?";
    private static final String SQL_SELECT_BY_ROLE = "SELECT login, password FROM users WHERE roles_role_name = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO users (login, password, roles_role_name) VALUES (?,?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, password=?, roles_role_name=? WHERE login=?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password=? WHERE login=?";


    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString(USERS_LOGIN));
                user.setPassword(resultSet.getNString(USERS_PASSWORD));
                user.setRole(UserRole.valueOf(resultSet.getString(USERS_ROLES_ROLE_NAME).toUpperCase()));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public User findEntity(String id) throws DaoException {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setLogin(resultSet.getString(USERS_LOGIN));
            user.setPassword(resultSet.getString(USERS_PASSWORD));
            user.setRole(UserRole.valueOf(resultSet.getString(USERS_ROLES_ROLE_NAME).toUpperCase()));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public boolean delete(String id) throws DaoException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setString(1, id);
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
    public boolean create(User user) throws DaoException {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().toString().toLowerCase());
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
    public User update(User userUpdate) throws DaoException {
        User user = findEntity(userUpdate.getLogin());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, userUpdate.getLogin());
            preparedStatement.setString(2, userUpdate.getPassword());
            preparedStatement.setString(3, userUpdate.getRole().toString().toLowerCase());
            preparedStatement.setString(4, userUpdate.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public User updatePasswordByLogin(User userUpdate) throws DaoException {
        User user = findEntity(userUpdate.getLogin());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PASSWORD);
            preparedStatement.setString(1, userUpdate.getPassword());
            preparedStatement.setString(2, userUpdate.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public List<User> findByRole(UserRole role) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = CustomConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ROLE);
            preparedStatement.setString(1, String.valueOf(role).toLowerCase());//????
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString(USERS_LOGIN));
                user.setPassword(resultSet.getString(USERS_PASSWORD));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return users;
    }
}
