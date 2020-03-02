package com.alshevskaya.cleaningcompany.command.impl;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.UserServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.PARAM_NAME_LOGIN;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.PARAM_NAME_PASSWORD;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserServiceImpl userService;

    public LoginCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Gets login and password values from the request.
     * Validates this values, if input data is not valid, returns router to the same page
     * with message about invalid login or password.
     * If user with such login doesn't exist or login doesn't match password, returns router
     * to the same page with message about wrong login or password.
     * Otherwise, finds the user by this values and sets sessions attributes and
     * returns router to the user's cabinet page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#validateLogin(String)
     * @see DataValidator#validatePassword(String)
     * @see UserServiceImpl#isLoginExist(String)
     * @see UserServiceImpl#checkLogin(String, String)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        DataValidator validator = new DataValidator();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        if (validator.validateLogin(login) && validator.validatePassword(password)) {
            try {
                if (userService.isLoginExist(login)) {
                    if (userService.checkLogin(login, password)) {
                        UserRole userRole = userService.checkUserRole(login);
                        request.setAttribute(ATTRIBUTE_USER, login);
                        request.getSession().setAttribute(ATTRIBUTE_USER_ROLE, userRole);
                        request.getSession().setAttribute(ATTRIBUTE_USER, login);
                        request.getSession().setAttribute(ATTRIBUTE_PASSWORD, password);
                        switch (userRole) {
                            case CLIENT:
                                router.setPagePath(PathForJsp.CLIENT_CABINET.getPath());
                                router.setType(RouteType.FORWARD);
                                break;
                            case CLEANER:
                                router.setPagePath(PathForJsp.CLEANER_CABINET.getPath());
                                router.setType(RouteType.FORWARD);
                                break;
                            case ADMINISTRATOR:
                                router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
                                router.setType(RouteType.FORWARD);
                                break;
                            default:
                                router.setPagePath(PathForJsp.GUEST.getPath());
                                router.setType(RouteType.REDIRECT);
                        }
                    } else {
                        request.setAttribute(ATTRIBUTE_WRONG_ACTION, MESSAGE_WRONG_ACTION_LOGIN);
                        router.setPagePath(PathForJsp.LOGIN.getPath());
                        router.setType(RouteType.FORWARD);
                    }
                } else {
                    request.setAttribute(ATTRIBUTE_WRONG_ACTION, MESSAGE_WRONG_ACTION_LOGIN);
                    router.setPagePath(PathForJsp.LOGIN.getPath());
                    router.setType(RouteType.FORWARD);
                }
            } catch (ServiceException e) {
                logger.error("Error while executing command", e);
                router.setPagePath(PathForJsp.ERROR.getPath());
                router.setType(RouteType.FORWARD);
            }
        } else {
            request.setAttribute(ATTRIBUTE_INVALID_LOGIN_PASSWORD, MESSAGE_INVALID_LOGIN_PASSWORD);
            router.setPagePath(PathForJsp.LOGIN.getPath());
            router.setType(RouteType.FORWARD);
        }
        return router;
    }
}
