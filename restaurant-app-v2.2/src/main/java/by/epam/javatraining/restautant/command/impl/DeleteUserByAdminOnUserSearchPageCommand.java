package by.epam.javatraining.restautant.command.impl;


import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletResponse;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.UserService;

public class DeleteUserByAdminOnUserSearchPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserByAdminOnUserSearchPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        UserService service = ServiceFactory.INSTANCE.getUserService();

        try {
            service.deleteUserById(userId);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return PageType.ADMIN_USER_SEARCH.getValue();
    }
}
