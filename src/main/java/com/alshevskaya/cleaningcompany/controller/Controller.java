package com.alshevskaya.cleaningcompany.controller;

import com.alshevskaya.cleaningcompany.command.*;
import com.alshevskaya.cleaningcompany.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.alshevskaya.cleaningcompany.command.ConstantName.ATTRIBUTE_NULL_PAGE;
import static com.alshevskaya.cleaningcompany.command.ConstantName.MESSAGE_NULL_PAGE;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        super.init();
        CustomConnectionPool pool = CustomConnectionPool.INSTANCE;
        logger.debug("Initializing controller");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter("command"));
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            Router router = command.execute(request);
            if (router != null) {
                if (router.getType().equals(RouteType.FORWARD)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + router.getPagePath());
                }
            } else {
                request.getSession().setAttribute(ATTRIBUTE_NULL_PAGE, MESSAGE_NULL_PAGE);
                response.sendRedirect(request.getContextPath() + PathForJsp.ERROR.getPath());
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        CustomConnectionPool.INSTANCE.destroyPool();
        logger.debug("Destroying controller");
    }
}
