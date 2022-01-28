package by.epam.javatraining.restautant.filter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.entity.User;

@WebFilter(urlPatterns = {"/jsp/admin/*"})
public class AdministrationAccessFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AdministrationAccessFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(JSPParameter.USER.getValue());

        if (user == null || user.getRole().getRoleId() != 1) {
            request.getRequestDispatcher("/controller").forward(req, res);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("Filter destroyed");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Filter initialize");
    }
}
