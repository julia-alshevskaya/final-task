package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.CleanerDaoImpl;
import com.alshevskaya.cleaningcompany.dao.impl.ClientDaoImpl;
import com.alshevskaya.cleaningcompany.dao.impl.OrderDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.entity.Client;
import com.alshevskaya.cleaningcompany.entity.Order;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private OrderDaoImpl orderDao;
    private static final int NUMBER_ONE = 1;

    public OrderServiceImpl(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public boolean isIdExist(String id) throws ServiceException {
        List<Order> orders = findAll().stream()
                .filter(s -> s.getOrderId() == Integer.valueOf(id))
                .collect(Collectors.toList());
        return (orders.size() == NUMBER_ONE);
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> showClientsOrders(String login) throws ServiceException {
        ClientServiceImpl clientService = new ClientServiceImpl(new ClientDaoImpl());
        Client client = clientService.findEntityByLogin(login);
        Integer clientId = client.getClientId();
        return findAllClientOrdersByClientId(clientId);
    }

    @Override
    public List<Order> showCleanersCompletedOrders(String login) throws ServiceException {
        CleanerServiceImpl cleanerService = new CleanerServiceImpl(new CleanerDaoImpl());
        Cleaner cleaner = cleanerService.findEntityByLogin(login);
        Integer cleanerId = cleaner.getCleanerId();
        return findAllClientOrdersByCleanerId(cleanerId);
    }

    @Override
    public Order findEntity(Integer id) throws ServiceException {
        try {
            return orderDao.findEntity(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws ServiceException {
        try {
            return orderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Order order) throws ServiceException {
        try {
            return orderDao.create(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order update(Order order) throws ServiceException {
        try {
            return orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAllClientOrdersByClientId(Integer clientId) throws ServiceException {
        try {
            return orderDao.findAllClientOrdersByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAllClientOrdersByCleanerId(Integer cleanerId) throws ServiceException {
        try {
            return orderDao.findAllClientOrdersByCleanerId(cleanerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
