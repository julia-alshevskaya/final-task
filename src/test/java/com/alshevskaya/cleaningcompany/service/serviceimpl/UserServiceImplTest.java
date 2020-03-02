package com.alshevskaya.cleaningcompany.service.serviceimpl;

import com.alshevskaya.cleaningcompany.dao.impl.UserDaoImpl;
import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    private UserServiceImpl userService;
    private UserDaoImpl userDao;
    private final static String TEST_LOGIN ="vasenok@mail.ru";
    private final static String TEST_PASSWORD ="vasenok2017";
    private final static String TEST_PASSWORD_HASH ="646d467a5a573576617a49774d54633d";
    private final static String TEST_PASSWORD_UPDATE = "vasenok2000";
    @BeforeMethod
    public void setUp() {
        userDao = new UserDaoImpl();
        userService =new UserServiceImpl(userDao);
    }

    @AfterMethod
    public void tearDown() {
        userDao = null;
        userService = null;
    }

    @Test
    public void testCheсkUserRole() throws ServiceException {
        UserRole expected = UserRole.CLIENT;
        UserRole actual = userService.checkUserRole(TEST_LOGIN);
        assertEquals(actual,expected);
    }

    @Test
    public void testCheckLogin() throws ServiceException {
        Boolean actual = userService.checkLogin(TEST_LOGIN,TEST_PASSWORD);
        assertTrue(actual);
    }

    @Test
    public void testIsLoginExist() throws ServiceException {
        Boolean actual = userService.isLoginExist(TEST_LOGIN);
        assertTrue(actual);
    }

    @Test
    public void testFindAllUsers() throws ServiceException {
        List<User> userList = userService.findAllUsers();
        int expected = 19;
        int actual = userList.size();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByLogin() throws ServiceException {
        User expected = new User(TEST_LOGIN,TEST_PASSWORD_HASH, UserRole.CLIENT);
        User actual = userService.findByLogin(TEST_LOGIN);
        assertEquals(actual, expected);
    }

//    @Test
//    public void testUpdatePasswordByLogin() throws ServiceException {
//        User expected = new User(TEST_LOGIN,TEST_PASSWORD_HASH, UserRole.CLIENT);
//        User updateUser = new User (TEST_LOGIN, TEST_PASSWORD_UPDATE,UserRole.CLIENT );
//        User actual = userService.update(updateUser);
//    }

    @Test
    public void testFindByRole() throws ServiceException {
        List<User> userList = userService.findByRole(UserRole.ADMINISTRATOR);
        int expected = 1;
        int actual = userList.size();
        assertEquals(actual, expected);
    }
}