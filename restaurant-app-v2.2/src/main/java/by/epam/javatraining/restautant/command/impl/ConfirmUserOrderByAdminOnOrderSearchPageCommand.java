package by.epam.javatraining.restautant.command.impl;


import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletResponse;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.OrderService;

public class ConfirmUserOrderByAdminOnOrderSearchPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ConfirmUserOrderByAdminOnOrderSearchPageCommand.class);
    private static final int CONFIRMED_ORDER_STATUS_ID = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = null;
        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));

        try {
            order = service.getOrderById(orderId);
            order.setOrderStatusId(CONFIRMED_ORDER_STATUS_ID);
            service.updateOrder(order);

        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return PageType.ADMIN_ORDER_SEARCH.getValue();
    }
}
