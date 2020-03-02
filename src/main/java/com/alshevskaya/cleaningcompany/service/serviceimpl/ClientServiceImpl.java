package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.ClientDaoImpl;
import com.alshevskaya.cleaningcompany.dao.impl.UserDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Client;
import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.ClientService;
import com.alshevskaya.cleaningcompany.util.PasswordHashGenerator;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDaoImpl clientDao;

    public ClientServiceImpl(ClientDaoImpl clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public boolean addClient(String login, String password, String name, String surname,
                             String phone, String address) throws ServiceException {
        boolean flagCreateUser = false;
        boolean flagCreateClient = false;
        String hashPassword = PasswordHashGenerator.encryptPassword(password);
        User user = new User(login, hashPassword, UserRole.CLIENT);
        Client client = new Client(name, surname, phone, address, login);
        UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl());
        flagCreateUser = userService.addUser(user);
        flagCreateClient = create(client);
        return flagCreateUser && flagCreateClient;
    }

    @Override
    public boolean isLoginExist(String enterLogin) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl());
        return userService.isLoginExist(enterLogin);
    }

    @Override
    public List<Client> findAll() throws ServiceException {
        try {
            return clientDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Client findEntity(Integer id) throws ServiceException {
        try {
            return clientDao.findEntity(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Client findEntityByLogin(String login) throws ServiceException {
        try {
            return clientDao.findEntityByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Client client) throws ServiceException {
        try {
            return clientDao.create(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Client update(Client client) throws ServiceException {
        try {
            return clientDao.update(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
