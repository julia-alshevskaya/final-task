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
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;

public class ShowOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl orderService;

    public ShowOrdersCommand(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * Shows order list.
     * Sets the session attribute to show orders and
     * returns router to the show admin page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see OrderServiceImpl#findAll()
     */
    @Override
    public Router execute(HttpServletRequest request) {
        DataValidator validator = new DataValidator();
        Router router = new Router();
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderService.findAll();
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ERROR.getPath());
        }
        if (!orderList.isEmpty()) {
            request.setAttribute(ATTRIBUTE_ORDER_LIST, orderList);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
        } else {
            request.setAttribute(ATTRIBUTE_EMPTY_LIST, MESSAGE_EMPTY_ORDER_LIST);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
        }
        return router;
    }
}
