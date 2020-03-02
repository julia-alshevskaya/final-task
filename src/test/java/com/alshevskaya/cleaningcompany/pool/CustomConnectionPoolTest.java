package com.alshevskaya.cleaningcompany.pool;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CustomConnectionPoolTest {
    private CustomConnectionPool pool;

    @BeforeMethod
    public void setUp() {
        pool = CustomConnectionPool.INSTANCE;
    }

    @AfterMethod
    public void tearDown() {
        pool = null;
    }

    @Test
    public void testCheckPoolSize() {
        int expected = 32;
        int actual = pool.checkPoolSize();
        assertEquals(actual, expected);
    }

    @Test
    public void testDestroyPool() {
        pool.destroyPool();
        int expected = 0;
        int actual = pool.checkPoolSize();
        assertEquals(actual, expected);
    }
}