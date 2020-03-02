package com.alshevskaya.cleaningcompany.command;

public class ConstantName {
    private ConstantName() {
    }

    public final static String PARAMETER_LOCALE = "locale";
    public final static String ATTRIBUTE_NULL_PAGE = "nullPage";
    public final static String ATTRIBUTE_PAGE_PATH = "pagePath";
    public final static String ATTRIBUTE_USER = "user";
    public final static String ATTRIBUTE_MAIL = "errorSendMail";
    public final static String ATTRIBUTE_ERROR = "error";
    public final static String ATTRIBUTE_WRONG_ACTION = "wrongAction";
    public final static String ATTRIBUTE_MAP = "map";
    public final static String ATTRIBUTE_SIGN_UP_ERROR = "signupError";
    public final static String ATTRIBUTE_USER_ROLE = "userRole";
    public final static String ATTRIBUTE_PASSWORD = "password";
    public final static String ATTRIBUTE_INVALID_LOGIN_PASSWORD = "password";
    public final static String ATTRIBUTE_ERROR_LOGIN_PASSWORD = "errorLoginPasswordMessage";
    public final static String ATTRIBUTE_CLIENT = "client";
    public final static String ATTRIBUTE_UPDATE_DATA_ERROR = "updateDataError";
    public final static String ATTRIBUTE_ORDER_LIST = "orderList";
    public final static String ATTRIBUTE_EMPTY_LIST = "emptyList";
    public final static String ATTRIBUTE_CLEANER = "cleaner";
    public final static String ATTRIBUTE_ERROR_DATA = "errorData";
    public final static String ATTRIBUTE_ERROR_SIGN_UP_STAFF = "signupCleanerError";
    public final static String ATTRIBUTE_STAFF_LIST = "staffList";
    public final static String ATTRIBUTE_SERVICE_LIST = "serviceList";
    public final static String ATTRIBUTE_CLIENT_LIST = "clientList";

    public final static String MESSAGE_LOCALE = "ru_RU";
    public final static String MESSAGE_RU = "ru_RU";
    public final static String MESSAGE_EN = "en_EN";
    public final static String MESSAGE_NULL_PAGE = "Page not found";
    public final static String MESSAGE_MAIL = "Send message failed";
    public final static String MESSAGE_ERROR = "Sign up failed";
    public final static String MESSAGE_WRONG_ACTION = "User with this login is already registered";
    public final static String MESSAGE_SIGN_UP_ERROR = "Invalid input data. Fill in this fields correctly.";
    public final static String MESSAGE_WRONG_ACTION_LOGIN = "Wrong login or password.";
    public final static String MESSAGE_INVALID_LOGIN_PASSWORD = "Invalid login or password.";
    public final static String MESSAGE_WRONG_ACTION_ACCOUNT_NOT_FOUND = "This account was not found";
    public final static String MESSAGE_ERROR_CHANGE_PASSWORD = "Change password failed";
    public final static String MESSAGE_ERROR_LOGIN_PASSWORD = "Password conformation failed";
    public final static String MESSAGE_ERROR_INVALID_LOGIN_PASSWORD = "Invalid login or password.";
    public final static String MESSAGE_ERROR_UPDATE_DATA = "Update data failed";
    public final static String MESSAGE_UPDATE_DATA_ERROR = "Invalid input data. Fill in this fields correctly.";
    public final static String MESSAGE_EMPTY_ORDER_LIST = "Order list is empty";
    public final static String MESSAGE_ERROR_SERCHING_DATA = "Error serching data";
    public final static String MESSAGE_UPDATE_STAFF_DATA_ERROR = "Invalid input data. Fill in this fields correctly.";
    public final static String MESSAGE_ERROR_DATA = "Fill in the fields correctly";
    public final static String MESSAGE_SIGN_UP_STAFF_ERROR = "Employee registration failed";
    public final static String MESSAGE_WRONG_ACTION_SIGNUP_STAFF = "Employee with this login is already registered";
    public final static String MESSAGE_ERROR_SIGN_UP_STAFF = "Invalid input data. Fill in this fields correctly.";
    public final static String MESSAGE_EMPTY_STAFF_LIST = "Cleaner list is empty";
    public final static String MESSAGE_EMPTY_CUSTOMER_LIST = "Customer list is empty";
    public final static String MESSAGE_EMPTY_CLIENT_LIST = "Client list is empty";
    public final static String MESSAGE_DELETE_SERVICE_ERROR = "Service with such id doesn't exist";
    public final static String MESSAGE_DELETE_ORDER_ERROR = "Order with such id doesn't exist";
}
