package by.epam.javatraining.restautant.command.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletResponse;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.UserService;

public class FindUserByEmailCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindUserByEmailCommand.class);
    private static final String EMPTY_USER_MESSAGE = "User with this email does not exist";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("userParameter");
        UserService service = ServiceFactory.INSTANCE.getUserService();
        User user = null;

        try {
            user = service.getUserByEmail(email);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        if (user == null) {
            request.setAttribute(JSPParameter.USER_SEARCH_MESSAGE.getValue(), EMPTY_USER_MESSAGE);
        }

        request.setAttribute(JSPParameter.USER_ID_REQUEST.getValue(), user);

        return PageType.ADMIN_USER_SEARCH.getValue();
    }
}
