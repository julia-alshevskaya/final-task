package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.ServiceDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Service;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.CleaningService;

import java.util.List;
import java.util.stream.Collectors;

public class CleaningServiceImpl implements CleaningService {
    private ServiceDaoImpl serviceDao;
    private static final int NUMBER_ONE = 1;

    public CleaningServiceImpl(ServiceDaoImpl serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public List<Service> findAll() throws ServiceException {
        try {
            return serviceDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isIdExist(String id) throws ServiceException {
        List<Service> services = findAll().stream()
                .filter(s -> s.getServiceId() == Integer.valueOf(id))
                .collect(Collectors.toList());
        return (services.size() == NUMBER_ONE);
    }

    @Override
    public Service findEntity(Integer id) throws ServiceException {
        try {
            return serviceDao.findEntity(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws ServiceException {
        try {
            return serviceDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Service service) throws ServiceException {
        try {
            return serviceDao.create(service);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Service update(Service service) throws ServiceException {
        try {
            return serviceDao.update(service);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
