package by.epam.javatraining.restautant.command.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.UserService;



public class UserSignInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserSignInCommand.class);
    private static final String LOGIN_ERROR_MESSAGE = "User does not exist or you entered incorrect password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String login = request.getParameter(JSPParameter.LOGIN.getValue());
        String password = request.getParameter(JSPParameter.PASSWORD.getValue());
        UserService service = ServiceFactory.INSTANCE.getUserService();

        try {
            user = service.signIn(login, password);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        HttpSession session = request.getSession();

        if (user != null) {
            session.setAttribute(JSPParameter.USER.getValue(), user);
            session.setAttribute(JSPParameter.ROLE.getValue(), user.getRole().getRoleId());
            return PageType.START_PAGE.getValue();
        } else {
            session.setAttribute(JSPParameter.LOGIN_ERROR.getValue(), LOGIN_ERROR_MESSAGE);
            return PageType.SIGN_IN_PAGE.getValue();
        }
    }
}
