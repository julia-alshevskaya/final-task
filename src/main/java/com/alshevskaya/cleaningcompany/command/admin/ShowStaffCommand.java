package com.alshevskaya.cleaningcompany.command.admin;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;
import com.alshevskaya.cleaningcompany.entity.Cleaner;
import com.alshevskaya.cleaningcompany.exception.ServiceException;
import com.alshevskaya.cleaningcompany.service.serviceimpl.CleanerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;

public class ShowStaffCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CleanerServiceImpl cleanerService;

    public ShowStaffCommand(CleanerServiceImpl cleanerService) {
        this.cleanerService = cleanerService;
    }

    /**
     * Shows staff list.
     * Sets the session attribute to show cleaners and
     * returns router to the show admin page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     * @see CleanerServiceImpl#findAll()
     */
    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        List<Cleaner> staffList = new ArrayList<>();
        try {
            staffList = cleanerService.findAll();
        } catch (ServiceException e) {
            logger.error("Error while executing command", e);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ERROR.getPath());
        }
        if (!staffList.isEmpty()) {
            request.setAttribute(ATTRIBUTE_STAFF_LIST, staffList);
            router.setType(RouteType.FORWARD);
            router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
        } else {
            request.setAttribute(ATTRIBUTE_EMPTY_LIST, MESSAGE_EMPTY_STAFF_LIST);
            router.setType(RouteType.REDIRECT);
            router.setPagePath(PathForJsp.ADMIN_CABINET.getPath());
        }
        return router;
    }
}
