package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.CleanerDaoImpl;
import com.alshevskaya.cleaningcompany.dao.impl.ClientDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.entity.Client;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ClientServiceImplTest {
    private ClientDaoImpl clientDao;
    private ClientServiceImpl clientService;

    @BeforeMethod
    public void setUp() {
        clientDao = new ClientDaoImpl();
        clientService = new ClientServiceImpl(clientDao);
    }

    @AfterMethod
    public void tearDown() {
        clientDao = null;
        clientService = null;
    }

//    @Test
//    public void testAddClient() throws ServiceException {
//        boolean actual =  clientService.addClient("arina2000@gmail.ru", "minsk2019", "Арина", "Барыня", "+375291258545", "ул Глебки 5, 36");
//        assertTrue(actual);
//    }

    @Test
    public void testIsLoginExist() throws ServiceException {
        boolean actual = clientService.isLoginExist("vasenok@mail.ru");
        assertTrue(actual);
    }

    @Test
    public void testFindAll() throws ServiceException {
        int expected = 10;
        int actual = clientService.findAll().size();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindEntity() throws ServiceException {
        Client expected = new Client(36, "Турандот", "Каблучкова", "+375291857421",
                "Шаранговича 25,36");
        Client actual = clientService.findEntity(36);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindEntityByLogin() throws ServiceException {
        Client expected = new  Client(36, "Турандот", "Каблучкова", "+375291857421",
                "Шаранговича 25,36", "trusik@mail.ru");
        Client actual = clientService.findEntityByLogin("trusik@mail.ru");
        assertEquals(actual, expected);
    }

//    @Test
//    public void testUpdate() throws ServiceException {
//        Client expected = new  Client(36, "Турандот", "Каблучкова", "+375291855555",
//                "Шаранговича 25,36", "trusik@mail.ru");
//        Client updateCleaner = new  Client("Турандот", "Каблучкова", "+375291811111",
//                "Шаранговича 25,36", "trusik@mail.ru");
//        Client actual = clientService.update(updateCleaner);
//        assertEquals(actual, expected);
//    }
}