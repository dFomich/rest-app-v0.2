package by.epam.javatraining.restautant.command.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.OrderService;

public class ViewUserOrdersCommands implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewUserOrdersCommands.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        List<Order> orderList;

        User user = (User) session.getAttribute(JSPParameter.USER.getValue());

        if (user != null) {
            try {
                orderList = service.getAllOrdersByUserId(user.getUserId());
                request.setAttribute(JSPParameter.USER_ORDER_LIST.getValue(), orderList);
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        }

        return PageType.PROFILE_USER_ORDERS.getValue();
    }
}
