package by.epam.javatraining.restautant.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.exception.PositionInitializeException;
import by.epam.javatraining.restautant.factory.CommandFactory;
import by.epam.javatraining.restautant.pool.ConnectionPool;
import by.epam.javatraining.restautant.util.PositionCash;



@WebServlet(urlPatterns = {"/controller"} )
public class ControllerServlet extends HttpServlet {
    private static Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    @Override
    public void init() {
        ConnectionPool.getInstance().initializeConnectionPool();
        PositionCash cash = PositionCash.getInstance();
        try {
            cash.initPositions();
        } catch (PositionInitializeException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Command command = CommandFactory.getInstance().spotCommand(request);
        String page = command.execute(request, response);
        String requestAttribute = (String) request.getAttribute(JSPParameter.REQUEST_TYPE.getValue());
        chooseResponseType(requestAttribute, page, request, response);
    }

    private void chooseResponseType
            (String requestAttribute, String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (requestAttribute == null) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
