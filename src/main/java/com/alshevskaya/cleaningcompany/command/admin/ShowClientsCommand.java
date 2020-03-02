package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.Client;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;

public class ShowClientsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private ClientServiceImpl clientService;

    public ShowClientsCommand(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    /**
     * Shows client list.
     * Sets the session attribute to show clients and
     * returns router to the show admin page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see ClientServiceImpl#findAll()
     */
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        List<Client> clientList = new ArrayList<>();
        try {
            clientList = clientService.findAll();
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ERROR.getPath());
        }
        if (!clientList.isEmpty()) {
            request.setAttribute(ATTRIBUTE_CLIENT_LIST, clientList);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
        } else {
            request.setAttribute(ATTRIBUTE_EMPTY_LIST, MESSAGE_EMPTY_CLIENT_LIST);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
        }
        return router;
    }
}
