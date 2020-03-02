package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.CleaningServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.PARAM_SERVICE_ID;

public class DeleteServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    CleaningServiceImpl cleaningService;

    public DeleteServiceCommand(CleaningServiceImpl cleaningService) {
        this.cleaningService = cleaningService;
    }

    /**
     * Gets serviceId
     * value from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Checks, if the service with this id doesn't exists, returns router to the same page with message
     * about service with such id doesn't exist.
     * Otherwise, delete service (delete entity and updates database),
     * returns router to the delete service page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#isValidId(String)
     * @see CleaningServiceImpl#isIdExist(String)
     * @see CleaningServiceImpl#delete(Integer)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        String serviceId = request.getParameter(PARAM_SERVICE_ID);
        if (validator.isValidId(serviceId)) {
            try {
                if (cleaningService.isIdExist(serviceId)) {
                    cleaningService.delete(Integer.valueOf(serviceId));
                    router.setType(RouteType.REDIRECT);
                    router.setPagePath(PathForJsp.DELETE_SERVICE.getPath());
                } else {
                    request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_DELETE_SERVICE_ERROR);
                    router.setType(RouteType.FORWARD);
                    router.setPagePath(PathForJsp.DELETE_SERVICE.getPath());
                }
            } catch (ServiceException e) {
                logger.error("Delete service failed", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_ERROR_DATA);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.DELETE_SERVICE.getPath());
        }
        return router;
    }
}
