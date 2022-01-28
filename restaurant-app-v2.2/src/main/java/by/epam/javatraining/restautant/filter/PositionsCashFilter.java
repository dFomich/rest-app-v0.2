package by.epam.javatraining.restautant.filter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.util.PositionCash;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class PositionsCashFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(PositionsCashFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        List<Position> positionList = PositionCash.getInstance().getPositionList();
        req.setAttribute(JSPParameter.POSITIONS.getValue(), positionList);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Filter initialize");
    }

    @Override
    public void destroy() {
        LOGGER.info("Filter destroyed");
    }
}
