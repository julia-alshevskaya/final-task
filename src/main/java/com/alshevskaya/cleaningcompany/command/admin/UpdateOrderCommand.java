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

public class UpdateOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl orderService;


    public UpdateOrderCommand(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * Gets orderId, dateOrder,price
     * values from the request.
     * Validates this values, if input data is not valid, returns router to the same page with message
     * about incorrect input data.
     * Update order's data.
     * returns router to the update order data page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see DataValidator#isValidParameter(String...)
     * @see OrderServiceImpl#update(Order)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        DataValidator validator = new DataValidator();
        String orderId = request.getParameter(PARAM_ORDER_ID);
        String dateOrder = request.getParameter(PARAM_DATE_ORDER);
        String price = request.getParameter(PARAM_PRICE_ORDER);
        if (validator.isValidParameter(orderId, dateOrder, price)) {
            Order order = new Order(Integer.parseInt(orderId), Timestamp.valueOf(dateOrder).toLocalDateTime(),
                    BigDecimal.valueOf(Long.valueOf(price)));
            try {
                orderService.update(order);
                router.setType(RouteType.REDIRECT);
                router.setPagePath(PathForJsp.UPDATE_ORDER.getPath());
            } catch (ServiceException e) {
                logger.error("Error while executing command", e);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.ERROR.getPath());
            }
        } else {
            request.setAttribute(ATTRIBUTE_ERROR_DATA, MESSAGE_ERROR_DATA);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.UPDATE_ORDER.getPath());
        }
        return router;
    }
}
