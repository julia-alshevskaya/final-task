package com.alshevskaya.cleaningcompany.command;

import javax.servlet.http.HttpServletRequest;

import static com.alshevskaya.cleaningcompany.command.ConstantName.*;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String currentParam = PARAMETER_LOCALE;
        String currentLocale = (String) request.getSession().getAttribute(currentParam);
        String changedLocale = currentLocale.equals(MESSAGE_RU) ? MESSAGE_EN
                : MESSAGE_RU;
        request.getSession().setAttribute(PARAMETER_LOCALE, changedLocale);
        String pageToGo = (String) request.getSession().getAttribute(ATTRIBUTE_PAGE_PATH);
        if (pageToGo == null) {
            pageToGo = PathForJsp.GUEST.getPath();
        }
        Router router = new Router();
        router.setType(RouteType.FORWARD);
        router.setPagePath(pageToGo);
        return router;
    }
}
