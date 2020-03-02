package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.Order;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.OrderServiceImpl;
import com.alshevskaya.cleaningcompany.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;
import static com.alshevskaya.cleaningcompany.command.ExtractedParameters.*;

public class AddOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl orderService;

    public AddOrderCommand(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * Gets dateOrder, price, clientId, cleanerId
     * values from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Add order (add entity and updates database),
     * returns router to the add order page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#isValidParameter(String...)
     * @see OrderServiceImpl#create(Order)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        String dateOrder = request.getParameter(PARAM_DATE_ORDER);
        String price = request.getParameter(PARAM_PRICE_ORDER);
        String clientId = request.getParameter(PARAM_CLIENT_ID);
        String cleanerId = request.getParameter(PARAM_CLEANER_ID);
        if (validator.isValidParameter(dateOrder, price, clientId, cleanerId)) {
            Order order = new Order(Timestamp.valueOf(dateOrder).toLocalDateTime(),
                    BigDecimal.valueOf(Long.valueOf(price)), Integer.valueOf(clientId), Integer.valueOf(cleanerId));
            try {
                orderService.create(order);
                router.setType(RouteType.REDIRECT);
                router.setPagePath(PathForJsp.ADD_ORDER.getPath());
            } catch (ServiceException e) {
                logger.error("Adding order failed ", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_ERROR_DATA);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ADD_ORDER.getPath());
        }
        return router;
    }
}
