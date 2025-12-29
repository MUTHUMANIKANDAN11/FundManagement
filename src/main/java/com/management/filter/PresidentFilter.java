package com.management.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.model.User;

@WebFilter("/president/*")
public class PresidentFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("User") : null;

        // Not logged in
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/LoginForm");
            return;
        }

        // Logged in but not HOD
        if (!"PRESIDENT".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
            return;
        }

        // Authorized
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
