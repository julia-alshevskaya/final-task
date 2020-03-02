package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.Service;
import com.alshevskaya.cleaningcompany.entity.UserRole;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.CleaningServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;

public class ShowServicesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CleaningServiceImpl cleaningService;

    public ShowServicesCommand(CleaningServiceImpl cleaningService) {
        this.cleaningService = cleaningService;
    }

    /**
     * Shows services list.
     * Sets the session attribute to show services
     * Get the session attribute userRole and
     * returns router to the user page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see CleaningServiceImpl#findAll()
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        List<Service> serviceList = new ArrayList<>();
        try {
            serviceList = cleaningService.findAll();
        } catch (ServiceException e) {
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ERROR.getPath());
            logger.error("Error serching data", e);
        }
        if (!serviceList.isEmpty()) {
            request.setAttribute(ATTRIBUTE_SERVICE_LIST, serviceList);
            UserRole userRole = (UserRole) request.getSession().getAttribute(ATTRIBUTE_USER_ROLE);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.SHOW_SERVICES.getPath());
        } else {
            request.setAttribute(ATTRIBUTE_EMPTY_LIST, MESSAGE_EMPTY_CUSTOMER_LIST);
            UserRole userRole = (UserRole) request.getSession().getAttribute(ATTRIBUTE_USER_ROLE);
            switch (userRole) {
                case CLIENT:
                    router.setType(RouteType.FORWARD);
                    router.setPagePath(PathForJsp.CLIENT_CABINET.getPath());
                    break;
                case CLEANER:
                    router.setType(RouteType.FORWARD);
                    router.setPagePath(PathForJsp.CLEANER_CABINET.getPath());
                    break;
                case ADMINISTRATOR:
                    router.setType(RouteType.FORWARD);
                    router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
                    break;
            }
        }
        return router;
    }
}
