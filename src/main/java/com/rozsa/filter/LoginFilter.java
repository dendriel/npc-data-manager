package com.rozsa.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
        // NOOP.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginUri = req.getContextPath() + "/login.jsp";
        String loginServlet = req.getContextPath() + "/login";

        if (isAuthenticated(session) || isLoginRequest(req, loginUri, loginServlet)) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginUri);
        }
    }

    private boolean isAuthenticated(HttpSession session) {
        Boolean isAuthenticated = false;
        if (session != null && session.getAttribute("authenticated") != null) {
            isAuthenticated = (Boolean)session.getAttribute("authenticated");
        }

        return isAuthenticated;
    }

    private boolean isLoginRequest(HttpServletRequest request, String loginUri, String loginServlet) {
        return request.getRequestURI().equals(loginUri) || request.getRequestURI().equals(loginServlet);
    }

    @Override
    public void destroy() {
        // NOOP.
    }
}
