package by.epam.javatraining.restautant.command.impl;


import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javatraining.restautant.builder.UserBuilder;
import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.UserService;

public class UserRegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserRegistrationCommand.class);
    private static final int DEFAULT_USER_ROLE_ID = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = createUserFromRegistrationForm(request);
        HttpSession session = request.getSession();
        UserService service = ServiceFactory.INSTANCE.getUserService();

        try {
            service.registerUser(user);
        } catch (ServiceException e) {
            session.setAttribute(JSPParameter.REGISTRATION_ERROR.getValue(), e.getMessage());
            LOGGER.error(e);
            return PageType.REGISTRATION_PAGE.getValue();
        }

        return PageType.SUCCESSFUL_REGISTRATION_MESSAGE.getValue();
    }

    private User createUserFromRegistrationForm(HttpServletRequest request) {
        return new UserBuilder()
                .buildLogin(request.getParameter(JSPParameter.LOGIN.getValue()))
                .buildFirstName(request.getParameter(JSPParameter.FIRST_NAME.getValue()))
                .buildLastName(request.getParameter(JSPParameter.LAST_NAME.getValue()))
                .buildEmail(request.getParameter(JSPParameter.EMAIL.getValue()))
                .buildPhoneNumber(request.getParameter(JSPParameter.PHONE_NUMBER.getValue()))
                .buildPassword(request.getParameter(JSPParameter.PASSWORD.getValue()))
                .buildRole(DEFAULT_USER_ROLE_ID)
                .build();
    }
}
