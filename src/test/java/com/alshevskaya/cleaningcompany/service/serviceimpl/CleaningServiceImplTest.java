package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.ServiceDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Service;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class CleaningServiceImplTest {
private CleaningServiceImpl cleaningService;
private ServiceDaoImpl serviceDao;
    @BeforeMethod
    public void setUp() {
        serviceDao = new ServiceDaoImpl();
        cleaningService = new CleaningServiceImpl(serviceDao);
    }

    @AfterMethod
    public void tearDown() {
        cleaningService=null;
        serviceDao= null;
    }

    @Test
    public void testFindAll() throws DaoException {
        int expected = 9;
        int actual = serviceDao.findAll().size();
        assertEquals(actual, expected);
    }

    @Test
    public void testIsIdExist() throws ServiceException {
        boolean actual = cleaningService.isIdExist("9");
        assertTrue(actual);
    }

    @Test
    public void testFindEntity() throws ServiceException {
        Service expected = new Service(1,"Уборка квартиры",new BigDecimal(15.00).setScale(2,2),1.0);
        Service actual = cleaningService.findEntity(1);
        assertEquals(actual,expected);
    }

//    @Test
//    public void testDelete() throws ServiceException {
//        boolean actual = cleaningService.delete(17);
//        assertTrue(actual);
//    }

    @Test
    public void testCreate() throws ServiceException {
        Service service = new Service("Выгул домашнего питомца",BigDecimal.valueOf(7.00),1.0);
        boolean actual = cleaningService.create(service);
        assertTrue(actual);
    }

//    @Test
//    public void testUpdate() throws ServiceException {
//        Service expected = new Service(19,"Выгул домашнего питомца",BigDecimal.valueOf(7.00),1.0);
//        Service updateService = new Service(19,"выгул домашнего питомца",BigDecimal.valueOf(7.00),1.0);
//        Service actual = cleaningService.update(updateService);
//    }
}