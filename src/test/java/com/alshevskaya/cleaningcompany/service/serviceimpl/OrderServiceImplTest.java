package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.OrderDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Order;
import com.alshevskaya.cleaningcompany.entity.Service;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OrderServiceImplTest {
    private OrderDaoImpl orderDao;
    private OrderServiceImpl orderService;

    @BeforeMethod
    public void setUp() {
        orderDao = new OrderDaoImpl();
        orderService = new OrderServiceImpl(orderDao);
    }

    @AfterMethod
    public void tearDown() {
        orderDao = null;
        orderService = null;
    }

    @Test
    public void testIsIdExist() throws ServiceException {
        boolean actual = orderService.isIdExist("9");
        assertTrue(actual);
    }

    @Test
    public void testFindAll() throws ServiceException {
        int expected = 12;
        int actual = orderService.findAll().size();
        assertEquals(actual, expected);
    }

    @Test
    public void testShowClientsOrders() throws ServiceException {
        int expected = 3;
        int actual = orderService.showClientsOrders("tttthanna@mail.ru").size();
        assertEquals(actual, expected);
    }

    @Test
    public void testShowCleanersCompletedOrders() throws ServiceException {
        int expected = 3;
        int actual = orderService.showCleanersCompletedOrders("turandot@mail.ru").size();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindEntity() throws ServiceException {
        Order expected = new Order(9, LocalDateTime.of(2010,07,19,
                8,25,25),new BigDecimal(95.00).setScale(2,2),37,18);
        Order actual = orderService.findEntity(9);
        assertEquals(actual,expected);
    }

//    @Test
//    public void testDelete() throws ServiceException {
//        boolean actual = orderService.delete(20);
//        assertTrue(actual);
//    }

    @Test
    public void testCreate() throws ServiceException {
        Order order = new Order( LocalDateTime.of(2019,07,29,13,00,15),BigDecimal.valueOf(105.00),37,18);
        boolean actual = orderService.create(order);
        assertTrue(actual);
    }

//    @Test
//    public void testUpdate() throws ServiceException {
//        Order order = new Order( 21,LocalDateTime.of(2019,07,29,13,00,15),BigDecimal.valueOf(105.00),37,18);
//        Order updateOrder = new Order( 21,LocalDateTime.of(2019,07,29,13,00,15),BigDecimal.valueOf(105.00),37,18);
//        Order actual = orderService.update(updateOrder);
//    }
}