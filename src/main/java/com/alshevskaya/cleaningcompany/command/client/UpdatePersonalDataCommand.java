package com.alshevskaya.cleaningcompany.command.client;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.Client;
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

public class UpdatePersonalDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private ClientServiceImpl clientService;

    public UpdatePersonalDataCommand(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    /**
     * Gets name, surname, phone and address
     * values from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Update client's data.
     * Sets the session attribute client
     * returns router to the update client data page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#validateUpdateData(Map)
     * @see DataValidator#isValidParameter(String...)
     * @see ClientServiceImpl#update(Client)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        Map<String, String> clientUpdateParameters = new HashMap<>();
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String address = request.getParameter(PARAM_NAME_ADDRESS);
        clientUpdateParameters.put(PARAM_NAME_NAME, name);
        clientUpdateParameters.put(PARAM_NAME_SURNAME, surname);
        clientUpdateParameters.put(PARAM_NAME_PHONE, phone);
        clientUpdateParameters.put(PARAM_NAME_ADDRESS, address);
        validator.validateUpdateData(clientUpdateParameters);
        if (validator.isValidParameter(name, surname, phone, address)) {
            try {
                String login = (String) request.getSession().getAttribute(ATTRIBUTE_USER);
                Client client = new Client(name, surname, address, phone, login);
                clientService.update(client);
                request.getSession().setAttribute(ATTRIBUTE_CLIENT, client);
                router.setType(RouteType.REDIRECT);
                router.setPagePath(PathForJsp.UPDATE_PERSONAL_DATA.getPath());
            } catch (ServiceException e) {
                request.setAttribute(ATTRIBUTE_ERROR, MESSAGE_ERROR_UPDATE_DATA);
                logger.error("Error while executing command", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_MAP, clientUpdateParameters);
            request.setAttribute(ATTRIBUTE_UPDATE_DATA_ERROR, MESSAGE_UPDATE_DATA_ERROR);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.UPDATE_PERSONAL_DATA.getPath());
        }
        return router;
    }
}
