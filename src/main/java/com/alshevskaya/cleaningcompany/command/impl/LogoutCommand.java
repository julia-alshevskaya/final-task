package com.alshevskaya.cleaningcompany.command.impl;

import com.alshevskaya.cleaningcompany.command.Command;
import com.alshevskaya.cleaningcompany.command.PathForJsp;
import com.alshevskaya.cleaningcompany.command.RouteType;
import com.alshevskaya.cleaningcompany.command.Router;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    public LogoutCommand() {
    }

    /**
     * Invalidates user session.
     * Returns router to the main page.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @return a {@code Router} object
     */
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        router.setPagePath(PathForJsp.INDEX.getPath());
        return router;
    }
}
