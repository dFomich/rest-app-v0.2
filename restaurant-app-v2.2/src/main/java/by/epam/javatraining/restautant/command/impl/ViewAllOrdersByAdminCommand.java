package by.epam.javatraining.restautant.command.impl;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.OrderService;



public class ViewAllOrdersByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewAllOrdersByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        List<Order> allUsersOrders = null;

        try {
            allUsersOrders = service.getAllOrders();
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.ALL_USERS_ORDERS.getValue(), allUsersOrders);

        return PageType.ADMIN_ALL_USERS_ORDERS.getValue();
    }
}
