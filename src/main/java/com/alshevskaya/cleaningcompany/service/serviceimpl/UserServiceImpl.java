package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.UserDaoImpl;
import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.UserService;
import com.alshevskaya.cleaningcompany.util.PasswordHashGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao;
    private static final int NUMBER_ONE = 1;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserRole checkUserRole(String enterLogin) throws ServiceException {
        User user = findByLogin(enterLogin);
        return user.getRole();
    }

    @Override
    public boolean checkLogin(String enterLogin, String enterPassword) throws ServiceException {
        String hashPassword = PasswordHashGenerator.encryptPassword(enterPassword);
        User user = findByLogin(enterLogin);
        return user.getLogin().equals(enterLogin) && user.getPassword().equals(hashPassword);
    }

    @Override
    public boolean isLoginExist(String enterLogin) throws ServiceException {
        List<User> users = findAllUsers().stream()
                .filter(s -> s.getLogin().equals(enterLogin))
                .collect(Collectors.toList());
        return (users.size() == NUMBER_ONE);
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        try {
            return userDao.findEntity(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        try {
            return userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User updatePasswordByLogin(User user) throws ServiceException {
        String encryptedPassword = PasswordHashGenerator.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        try {
            return userDao.updatePasswordByLogin(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByRole(UserRole role) throws ServiceException {
        try {
            return userDao.findByRole(role);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
