package com.alshevskaya.cleaningcompany.command.cleaner;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.Order;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;

public class ShowOrdersByCleanerIdCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl orderService;

    public ShowOrdersByCleanerIdCommand(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * Shows all cleaner's orders.
     * Sets the session attribute to show orders and
     * returns router to the show cleaner's orders page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see OrderServiceImpl#showCleanersCompletedOrders(String)
     */
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String login = (String) request.getSession().getAttribute(ATTRIBUTE_USER);
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderService.showCleanersCompletedOrders(login);
            if (!orderList.isEmpty()) {
                request.setAttribute(ATTRIBUTE_ORDER_LIST, orderList);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.SHOW_ORDERS_BY_CLEANER_ID.getPath());
            } else {
                request.setAttribute(ATTRIBUTE_EMPTY_LIST, MESSAGE_EMPTY_ORDER_LIST);
                router.setType(RouteType.FORWARD);
                router.setPagePath(PathForJsp.SHOW_ORDERS_BY_CLEANER_ID.getPath());
            }
        } catch (ServiceException e) {
            request.setAttribute(ATTRIBUTE_USER, MESSAGE_ERROR_SERCHING_DATA);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ERROR.getPath());
            logger.error("Error while executing command", e);
        }
        return router;
    }
}
