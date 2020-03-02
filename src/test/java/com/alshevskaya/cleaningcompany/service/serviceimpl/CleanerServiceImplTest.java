package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.CleanerDaoImpl;
import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.DaoException;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CleanerServiceImplTest {
    private CleanerDaoImpl cleanerDao;
    private CleanerServiceImpl cleanerService;

    @BeforeMethod
    public void setUp() {
        cleanerDao = new CleanerDaoImpl();
        cleanerService = new CleanerServiceImpl(cleanerDao);
    }

    @AfterMethod
    public void tearDown() {
        cleanerDao = null;
        cleanerService = null;
    }

//    @Test
//    public void testAddCleaner() throws ServiceException {
//        boolean actual = cleanerService.addCleaner("epanchinalss@gmail.ru", "minsk2019",
//                "cleaner", "Елизаветта", "Епанчина", "+375296541285", "ул Шагала 5, 36");
//        assertTrue(actual);
//    }

    @Test
    public void testIsLoginExist() throws ServiceException {
        boolean actual = cleanerService.isLoginExist("epanchinaliss@gmail.ru");
        assertTrue(actual);
    }

    @Test
    public void testFindAll() throws ServiceException {
        int expected = 9;
        int actual = cleanerService.findAll().size();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindEntity() throws ServiceException {
        Cleaner expected = new Cleaner(21, "Елизаветта", "Епанчина", "+375296541285",
                "ул Шагала 5, 36");
        Cleaner actual = cleanerService.findEntity(21);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindEntityByLogin() throws ServiceException {
        Cleaner expected = new Cleaner(21, "Елизаветта", "Епанчина", "+375296541285", "ул Шагала 5, 36", "epanchinaliss@gmail.ru");
        Cleaner actual = cleanerService.findEntityByLogin("epanchinaliss@gmail.ru");
        assertEquals(actual, expected);
    }

//    @Test
//    public void testUpdate() throws ServiceException {
//        Cleaner expected = new Cleaner(21,"Елизаветта", "Епанчина", "+37529654573", "ул Шагала 5, 36", "epanchinaliss@gmail.ru");
//        Cleaner updateCleaner = new Cleaner("Елизаветта", "Епанчина", "+375291111252", "ул Шагала 5, 36", "epanchinaliss@gmail.ru");
//        Cleaner actual = cleanerService.update(updateCleaner);
//        assertEquals(actual, expected);
//    }
}