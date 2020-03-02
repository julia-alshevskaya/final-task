package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.OrderServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.PARAM_ORDER_ID;

public class DeleteOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    OrderServiceImpl orderService;

    public DeleteOrderCommand(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * Gets orderId
     * value from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Checks, if the service with this id doesn't exists, returns router to the same page with message
     * about order with such id doesn't exist.
     * Otherwise, delete order (delete entity and updates database),
     * returns router to the delete order page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#isValidId(String)
     * @see OrderServiceImpl#isIdExist(String)
     * @see OrderServiceImpl#delete(Integer)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        String orderId = request.getParameter(PARAM_ORDER_ID);
        if (validator.isValidId(orderId)) {

            try {
                if (orderService.isIdExist(orderId)) {
                    orderService.delete(Integer.valueOf(orderId));
                    router.setType(RouteType.REDIRECT);
                    router.setPagePath(PathForJsp.DELETE_ORDER.getPath());
                } else {
                    request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_DELETE_ORDER_ERROR);
                    router.setType(RouteType.FORWARD);
                    router.setPagePath(PathForJsp.DELETE_ORDER.getPath());
                }
            } catch (ServiceException e) {
                logger.error("Delete order failed", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_ERROR_DATA);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.DELETE_ORDER.getPath());
        }
        return router;
    }
}
