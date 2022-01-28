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

import java.util.List;

public class ViewAllUsersByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewAllUsersByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService service = ServiceFactory.INSTANCE.getUserService();
        List<User> userList = null;

        try {
            userList = service.getAllUsers();
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.ALL_USERS.getValue(), userList);

        return PageType.ADMIN_ALL_USERS.getValue();
    }
}
