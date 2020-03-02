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

public class UpdateServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CleaningServiceImpl cleaningService;

    public UpdateServiceCommand(CleaningServiceImpl cleaningService) {
        this.cleaningService = cleaningService;
    }

    /**
     * Gets serviceId, serviceName, pricePerItem and quantity
     * values from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Update service's data.
     * returns router to the update service data page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#isValidParameter(String...)
     * @see CleaningServiceImpl#update(Service)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        String serviceId = request.getParameter(PARAM_SERVICE_ID);
        String serviceName = request.getParameter(PARAM_SERVICE_NAME);
        String pricePerItem = request.getParameter(PARAM_PRICE_PER_ITEM);
        String quantity = request.getParameter(PARAM_QUANTITY);
        if (validator.isValidParameter(serviceId, serviceName, pricePerItem, quantity)) {
            Service service = new Service(Integer.parseInt(serviceId), serviceName,
                    BigDecimal.valueOf(Long.parseLong(pricePerItem)), Double.parseDouble(quantity));
            try {
                cleaningService.update(service);
                router.setType(RouteType.REDIRECT);
                router.setPagePath(PathForJsp.UPDATE_SERVICE.getPath());
            } catch (ServiceException e) {
                logger.error("Error while executing command", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_ERROR_DATA);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.UPDATE_SERVICE.getPath());
        }
        return router;
    }
}