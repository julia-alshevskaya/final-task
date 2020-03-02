package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.Service;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.CleaningServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.*;

public class AddServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CleaningServiceImpl cleaningService;

    public AddServiceCommand(CleaningServiceImpl cleaningService) {
        this.cleaningService = cleaningService;
    }

    /**
     * Gets serviceName, pricePerItem, quantity
     * values from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Add service (add entity and updates database),
     * returns router to the add service page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#isValidParameter(String...)
     * @see CleaningServiceImpl#create(Service)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        String serviceName = request.getParameter(PARAM_SERVICE_NAME);
        String pricePerItem = request.getParameter(PARAM_PRICE_PER_ITEM);
        String quantity = request.getParameter(PARAM_QUANTITY);
        if (validator.isValidParameter(serviceName, pricePerItem, quantity)) {
            Service service = new Service(serviceName, BigDecimal.valueOf(Long.valueOf(pricePerItem)),
                    Double.valueOf(quantity));
            try {
                cleaningService.create(service);
                router.setType(RouteType.REDIRECT);
                router.setPagePath(PathForJsp.ADD_SERVICE.getPath());
            } catch (ServiceException e) {
                logger.error("Adding services failed failed", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_ERROR_DATA);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ADD_SERVICE.getPath());
        }
        return router;
    }
}
