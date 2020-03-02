package com.alshevskaya.cleaningcompany.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.alshevskaya.cleaningcompany.command.ConstantName.MESSAGE_LOCALE;
import static com.alshevskaya.cleaningcompany.command.ConstantName.PARAMETER_LOCALE;

@WebFilter(urlPatterns = {"/jsp/*"}, dispatcherTypes = {DispatcherType.FORWARD,
        DispatcherType.REQUEST})
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain next) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute(PARAMETER_LOCALE) == null) {
            session.setAttribute(PARAMETER_LOCALE, MESSAGE_LOCALE);
        }
        next.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {

    }
}