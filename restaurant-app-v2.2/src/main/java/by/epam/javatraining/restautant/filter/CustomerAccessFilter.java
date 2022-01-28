package by.epam.javatraining.restautant.filter;

import javax.servlet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.entity.User;

import java.io.IOException;

@WebFilter(urlPatterns = "/jsp/customer/*")
public class CustomerAccessFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CustomerAccessFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(JSPParameter.USER.getValue());

        if (user == null) {
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
