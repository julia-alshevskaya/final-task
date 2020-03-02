package com.alshevskaya.cleaningcompany.validator;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.*;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DataValidatorTest {
    private DataValidator validator;
    private final static String TEST_LOGIN = "vasenok@mail.ru";
    private final static String INVALID_TEST_LOGIN = "vasenokmail.ru";
    private final static String TEST_PASSWORD = "vasenok2017";
    private final static String TEST_CONFIRME_PASSWORD = "vasenok2017";
    private final static String INVALID_TEST_PASSWORD = "vasenok";
    private final static String INVALID_TEST_PHONE = "12j25";
    private final static String TEST_PARAMETR ="";
    @BeforeMethod
    public void setUp() {
        validator = new DataValidator();
    }

    @AfterMethod
    public void tearDown() {
        validator = null;
    }

    @Test
    public void testValidateLogin() {
        boolean actual = validator.validateLogin(TEST_LOGIN);
        assertTrue(actual);
    }

    @Test
    public void testNegativeValidateLogin() {
        boolean actual = validator.validateLogin(INVALID_TEST_LOGIN);
        assertTrue(actual);
    }

    @Test
    public void testValidatePassword() {
        boolean actual = validator.validatePassword(TEST_PASSWORD);
        assertTrue(actual);
    }

    @Test
    public void testNegativeValidatePassword() {
        boolean actual = validator.validatePassword(INVALID_TEST_PASSWORD);
        assertTrue(actual);
    }

    @Test
    public void testDoublePasswordCheck() {
        boolean actual = validator.doublePasswordCheck(TEST_PASSWORD, TEST_CONFIRME_PASSWORD);
        assertTrue(actual);
    }

    @Test
    public void testValidatePhone() {
        boolean actual = validator.validatePhone(INVALID_TEST_PHONE);
        assertFalse(actual);
    }

    @Test
    public void testIsValidParameter() {
        boolean actual = validator.isValidParameter(TEST_PARAMETR);
        assertFalse(actual);
    }

    @Test(dataProvider = "readingData")
    public void testValidateInputData( Map<String,String> expected) {
        Map<String,String> data = new HashMap<>();
        data.put(PARAM_NAME_LOGIN,"valintin@gmail.ru");
        data.put(PARAM_NAME_PASSWORD,"qwerty");
        data.put(PARAM_NAME_CONFIRMED_PASSWORD,"qwerty");
        data.put(PARAM_NAME_NAME,"Александр");
        data.put(PARAM_NAME_SURNAME,"Петров");
        data.put(PARAM_NAME_ADDRESS,"");
        data.put(PARAM_NAME_PHONE,"+375293638504");
        Map<String,String> actual = validator.validateInputData(data);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "readingData")
    public Object[][] readingData() {
        Map<String,String> data = new HashMap<>();
        data.put(PARAM_NAME_LOGIN,"valintin@gmail.ru");
        data.put(PARAM_NAME_PASSWORD,"");
        data.put(PARAM_NAME_CONFIRMED_PASSWORD,"");
        data.put(PARAM_NAME_NAME,"Александр");
        data.put(PARAM_NAME_SURNAME,"Петров");
        data.put(PARAM_NAME_ADDRESS,"");
        data.put(PARAM_NAME_PHONE,"+375293638504");
        return new Object[][]{
                {data}
        };
    }
}

