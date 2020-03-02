package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.CleanerServiceImpl;
import com.alshevskaya.cleaningcompany.service.serviceimpl.ClientServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.*;
import static com.alshevskaya.cleaningcompany.command.ConstantName.*;

public class SignUpStaffCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CleanerServiceImpl cleanerService;

    public SignUpStaffCommand(CleanerServiceImpl cleanerService) {
        this.cleanerService = cleanerService;
    }

    /**
     * Gets login, password, confirmed password, name, surname, phone and address
     * values from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Checks, if the user with this login already exists, returns router to the same page with message
     * about login already exists.
     * Otherwise, register new user (creates entity and updates database),
     * sets sessions attributes for current user and
     * returns router to the client cabinet page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#validateInputData(Map)
     * @see DataValidator#validatePassword(String)
     * @see DataValidator#validateLogin(String)
     * @see DataValidator#doublePasswordCheck(String, String)
     * @see DataValidator#validatePhone(String)
     * @see ClientServiceImpl#isLoginExist(String)
     * @see CleanerServiceImpl#addCleaner(String, String, String, String, String, String, String)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        Map<String, String> cleanerParameters = new HashMap<>();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = request.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String address = request.getParameter(PARAM_NAME_ADDRESS);
        String role = request.getParameter(PARAM_USER_ROLE);
        cleanerParameters.put(PARAM_NAME_LOGIN, login);
        cleanerParameters.put(PARAM_NAME_PASSWORD, password);
        cleanerParameters.put(PARAM_NAME_CONFIRMED_PASSWORD, confirmedPassword);
        cleanerParameters.put(PARAM_NAME_NAME, name);
        cleanerParameters.put(PARAM_NAME_SURNAME, surname);
        cleanerParameters.put(PARAM_NAME_PHONE, phone);
        cleanerParameters.put(PARAM_NAME_ADDRESS, address);
        validator.validateInputData(cleanerParameters);
        if (validator.validatePassword(password) && validator.validateLogin(login) && validator.doublePasswordCheck(password, confirmedPassword)
                && validator.validatePhone(phone) && validator.isValidParameter(name, surname, address)
                && (role.equals(UserRole.CLEANER.toString().toLowerCase()) || role.equals(UserRole.ADMINISTRATOR.toString().toLowerCase()))) {
            try {
                if (!cleanerService.isLoginExist(login)) {
                    if (cleanerService.addCleaner(login, password, role, name, surname, phone, address)) {
                        request.getSession().setAttribute(ATTRIBUTE_USER, login);
                        router.setType(RouteType.REDIRECT);
                        router.setPagePath(PathForJsp.SIGNUP_STAFF.getPath());
                    } else {
                        request.setAttribute(ATTRIBUTE_ERROR, MESSAGE_SIGN_UP_STAFF_ERROR);
                        router.setType(RouteType.FORWARD);
                        router.setPagePath(PathForJsp.ERROR.getPath());
                    }
                } else {
                    request.setAttribute(ATTRIBUTE_WRONG_ACTION, MESSAGE_WRONG_ACTION_SIGNUP_STAFF);
                    router.setType(RouteType.REDIRECT);
                    router.setPagePath(PathForJsp.SIGNUP_STAFF.getPath());
                }
            } catch (ServiceException e) {
                logger.error("Error while executing command", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_MAP, cleanerParameters);
            request.setAttribute(ATTRIBUTE_ERROR_SIGN_UP_STAFF, MESSAGE_ERROR_SIGN_UP_STAFF);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.SIGNUP_STAFF.getPath());
        }
        return router;
    }
}
