package com.alshevskaya.cleaningcompany.command.impl;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.User;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.UserServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.*;

public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserServiceImpl userService;

    public ChangePasswordCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = request.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);

        if (validator.validateLogin(login) && validator.validatePassword(password)
                && validator.validatePassword(confirmedPassword)) {
            if (password.equals(confirmedPassword)) {
                try {
                    if (userService.isLoginExist(login)) {
                        User user = new User(login, password);
                        userService.updatePasswordByLogin(user);
                        UserRole userRole = userService.checkUserRole(login);
                        request.getSession().setAttribute(ATTRIBUTE_USER, login);
                        request.getSession().setAttribute(ATTRIBUTE_USER_ROLE, userRole);
                        switch (userRole) {
                            case CLIENT:
                                router.setType(RouteType.REDIRECT);
                                router.setPagePath(PathForJsp.CLIENT_CABINET.getPath());
                                break;
                            case CLEANER:
                                router.setType(RouteType.REDIRECT);
                                router.setPagePath(PathForJsp.CLEANER_CABINET.getPath());
                                break;
                            case ADMINISTRATOR:
                                router.setType(RouteType.REDIRECT);
                                router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
                                break;
                            default:
                                router.setType(RouteType.FORWARD);
                                router.setPagePath(PathForJsp.GUEST.getPath());
                        }
                    } else {
                        request.setAttribute(ATTRIBUTE_WRONG_ACTION, MESSAGE_WRONG_ACTION_ACCOUNT_NOT_FOUND);
                        router.setType(RouteType.FORWARD);
                        router.setPagePath(PathForJsp.CHANGE_PASSWORD.getPath());
                    }
                } catch (ServiceException e) {
                    request.setAttribute(ATTRIBUTE_ERROR, MESSAGE_ERROR_CHANGE_PASSWORD);
                    router.setType(RouteType.FORWARD);
                    router.setPagePath(PathForJsp.ERROR.getPath());
                    logger.error("Change password failed", e);
                }
            } else {
                request.setAttribute(ATTRIBUTE_ERROR_LOGIN_PASSWORD, MESSAGE_ERROR_LOGIN_PASSWORD);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.CHANGE_PASSWORD.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_LOGIN_PASSWORD, MESSAGE_ERROR_INVALID_LOGIN_PASSWORD);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.CHANGE_PASSWORD.getPath());
        }
        return router;
    }
}
