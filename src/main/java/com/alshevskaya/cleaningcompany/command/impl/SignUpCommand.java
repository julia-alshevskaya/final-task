package com.alshevskaya.cleaningcompany.command.impl;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.email.SendEmail;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.ClientServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.*;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private ClientServiceImpl clientService;

    public SignUpCommand(ClientServiceImpl clientService) {
        this.clientService = clientService;
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
     * @see ClientServiceImpl#addClient(String, String, String, String, String, String)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        SendEmail sendEmail = new SendEmail();//TODO
        DataValidator validator = new DataValidator();
        Map<String, String> clientParameters = new HashMap<>();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = request.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String address = request.getParameter(PARAM_NAME_ADDRESS);
        clientParameters.put(PARAM_NAME_LOGIN, login);
        clientParameters.put(PARAM_NAME_PASSWORD, password);
        clientParameters.put(PARAM_NAME_CONFIRMED_PASSWORD, confirmedPassword);
        clientParameters.put(PARAM_NAME_NAME, name);
        clientParameters.put(PARAM_NAME_SURNAME, surname);
        clientParameters.put(PARAM_NAME_PHONE, phone);
        clientParameters.put(PARAM_NAME_ADDRESS, address);
        validator.validateInputData(clientParameters);
        if (validator.validatePassword(password) && validator.validateLogin(login) && validator.doublePasswordCheck(password, confirmedPassword)
                && validator.validatePhone(phone) && validator.isValidParameter(name, surname, address)) {
            try {
                if (!clientService.isLoginExist(login)) {
                    if (clientService.addClient(login, password, name, surname, phone, address)) {
                       // sendEmail.send(login, "testTopic", "testBody");//TODO
                        request.getSession().setAttribute(ATTRIBUTE_USER, name);
                        request.getSession().setAttribute(ATTRIBUTE_USER, login);
                        router.setType(RouteType.REDIRECT);
                        router.setPagePath(PathForJsp.CLIENT_CABINET.getPath());
                    } else {
                        request.setAttribute(ATTRIBUTE_MAIL, MESSAGE_MAIL);
                        request.setAttribute(ATTRIBUTE_ERROR, MESSAGE_ERROR);
                        router.setType(RouteType.FORWARD);
                        router.setPagePath(PathForJsp.ERROR.getPath());
                    }
                } else {
                    request.setAttribute(ATTRIBUTE_WRONG_ACTION, MESSAGE_WRONG_ACTION);
                    router.setType(RouteType.FORWARD);
                    router.setPagePath(PathForJsp.SIGNUP.getPath());
                }
            } catch (ServiceException e) {
                logger.error("Error while executing command", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_MAP, clientParameters);
            request.setAttribute(ATTRIBUTE_SIGN_UP_ERROR, MESSAGE_SIGN_UP_ERROR);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.SIGNUP.getPath());
        }
        return router;
    }
}
