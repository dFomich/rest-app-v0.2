package by.epam.javatraining.restautant.command.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.OrderService;


public class ViewUserOrdersByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewUserOrdersByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        List<Order> orderList = null;

        int userId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));

        try {
            orderList = service.getAllOrdersByUserId(userId);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.USER_ORDER_LIST.getValue(), orderList);

        return PageType.ADMIN_USER_ORDERS.getValue();
    }
}
