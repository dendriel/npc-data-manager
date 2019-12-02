package com.rozsa.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class NoCacheFilter implements Filter {
    @Override
    public void init(FilterConfig config) {
        // NOOP.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidated"); // HTTP 1.1
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0
        res.setHeader("Expires", "0"); // Proxies

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOOP.
    }
}
