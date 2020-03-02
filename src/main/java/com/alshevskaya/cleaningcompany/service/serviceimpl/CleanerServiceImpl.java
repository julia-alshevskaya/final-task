package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.CleanerDaoImpl;
import com.alshevskaya.cleaningcompany.dao.impl.UserDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.CleanerService;
import com.alshevskaya.cleaningcompany.util.PasswordHashGenerator;

import java.util.List;

public class CleanerServiceImpl implements CleanerService {
    private CleanerDaoImpl cleanerDao;

    public CleanerServiceImpl(CleanerDaoImpl cleanerDao) {
        this.cleanerDao = cleanerDao;
    }

    @Override
    public boolean addCleaner(String login, String password, String role, String name, String surname,
                              String phone, String address) throws ServiceException {
        boolean flagCreateUser = false;
        boolean flagCreateCleaner = false;
        String hashPassword = PasswordHashGenerator.encryptPassword(password);
        User user = new User(login, hashPassword, UserRole.valueOf(role.toUpperCase()));
        Cleaner cleaner = new Cleaner(name, surname, phone, address, login);
        UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl());
        flagCreateUser = userService.addUser(user);
        flagCreateCleaner = create(cleaner);
        return flagCreateUser && flagCreateCleaner;
    }

    @Override
    public boolean isLoginExist(String enterLogin) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl());
        return userService.isLoginExist(enterLogin);
    }

    @Override
    public List<Cleaner> findAll() throws ServiceException {
        try {
            return cleanerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Cleaner findEntity(Integer id) throws ServiceException {
        try {
            return cleanerDao.findEntity(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Cleaner findEntityByLogin(String login) throws ServiceException {
        try {
            return cleanerDao.findEntityByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Cleaner cleaner) throws ServiceException {
        try {
            return cleanerDao.create(cleaner);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Cleaner update(Cleaner cleaner) throws ServiceException {
        try {
            return cleanerDao.update(cleaner);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
