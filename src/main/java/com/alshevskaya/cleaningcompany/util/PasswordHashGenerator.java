package com.alshevskaya.cleaningcompany.util;

import java.math.BigInteger;
import java.util.Base64;

public class PasswordHashGenerator {
    private static final int NUMBER_ONE = 1;
    private static final int NUMBER_SIXTEEN = 16;

    public static String encryptPassword(String unencrypted) {
        byte[] bytes = null;
        Base64.Encoder encoder = Base64.getEncoder();
        bytes = encoder.encode(unencrypted.getBytes());
        BigInteger bigInteger = new BigInteger(NUMBER_ONE, bytes);
        String encrypted = bigInteger.toString(NUMBER_SIXTEEN);
        return encrypted;
    }
}
