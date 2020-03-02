package com.alshevskaya.cleaningcompany.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PasswordHashGeneratorTest {
    private PasswordHashGenerator hashGenerator;
    private String TEST_PASSWORD = "minsk2019";
    private String TEST_PASSWORD_HASH = "62576c75633273794d444535";

    @Test
    public void testEncryptPassword() {
        String expected = TEST_PASSWORD_HASH;
        String actual = PasswordHashGenerator.encryptPassword(TEST_PASSWORD);
        assertEquals(actual, expected);
    }
}