package com.alshevskaya.cleaningcompany.validator;

import java.util.Map;
import java.util.regex.Pattern;

import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.*;

public class DataValidator {
    private static final String REGEX_LOGIN = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String REGEX_PASSWORD = "(?=.*[\\d])(?=.*[a-z])(?=.*[A-Za-z]).{8,16}";
    private static final String REGEX_PHONE = "[+\\d]{3,}";
    private static final String REGEX_NUMBER = "[+\\d]{1,}";
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_FORTY_FIVE = 45;
    private static final int NUMBER_NINE = 9;


    public boolean validateLogin(String login) {
        return Pattern.matches(REGEX_LOGIN, login) &&
                (login != null && (login.length() > NUMBER_ZERO && login.length() <= NUMBER_FORTY_FIVE));
    }

    public boolean validatePassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password) &&
                (password != null && (password.length() > NUMBER_ZERO && password.length() <= NUMBER_FORTY_FIVE));
    }

    public boolean doublePasswordCheck(String password, String confirmePpassword) {
        return password.equals(confirmePpassword);
    }

    public boolean validatePhone(String phone) {
        return Pattern.matches(REGEX_PHONE, phone) && (phone != null && (phone.length() > NUMBER_ZERO && phone.length() <= NUMBER_FORTY_FIVE));
    }

    public boolean isValidParameter(String... parameters) {
        for (String param : parameters) {
            if (param == null || param.isEmpty() || param.length() >= NUMBER_FORTY_FIVE) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidId(String id) {
        return (id != null && (id.length() > NUMBER_ZERO && id.length() <= NUMBER_NINE) && Pattern.matches(REGEX_NUMBER, id));
    }

    public Map<String, String> validateInputData(Map<String, String> inputData) {
        if (!validateLogin(inputData.get(PARAM_NAME_LOGIN))) {
            inputData.put(PARAM_NAME_LOGIN, "");
        }
        if (!validatePassword(inputData.get(PARAM_NAME_PASSWORD))) {
            inputData.put(PARAM_NAME_PASSWORD, "");
        }
        if (!doublePasswordCheck(inputData.get(PARAM_NAME_PASSWORD),
                inputData.get(PARAM_NAME_CONFIRMED_PASSWORD))) {
            inputData.put(PARAM_NAME_CONFIRMED_PASSWORD, "");
        }
        if (!isValidParameter(inputData.get(PARAM_NAME_NAME))) {
            inputData.put(PARAM_NAME_NAME, "");
        }
        if (!isValidParameter(inputData.get(PARAM_NAME_SURNAME))) {
            inputData.put(PARAM_NAME_SURNAME, "");
        }
        if (!isValidParameter(inputData.get(PARAM_NAME_ADDRESS))) {
            inputData.put(PARAM_NAME_ADDRESS, "");
        }
        if (!isValidParameter(inputData.get(PARAM_NAME_PHONE))) {
            inputData.put(PARAM_NAME_PHONE, "");
        }
        return inputData;
    }

    public Map<String, String> validateUpdateData(Map<String, String> inputData) {
        if (!isValidParameter(inputData.get(PARAM_NAME_NAME))) {
            inputData.put(PARAM_NAME_NAME, "");
        }
        if (!isValidParameter(inputData.get(PARAM_NAME_SURNAME))) {
            inputData.put(PARAM_NAME_SURNAME, "");
        }
        if (!isValidParameter(inputData.get(PARAM_NAME_ADDRESS))) {
            inputData.put(PARAM_NAME_ADDRESS, "");
        }
        if (!isValidParameter(inputData.get(PARAM_NAME_PHONE))) {
            inputData.put(PARAM_NAME_PHONE, "");
        }
        return inputData;
    }
}
