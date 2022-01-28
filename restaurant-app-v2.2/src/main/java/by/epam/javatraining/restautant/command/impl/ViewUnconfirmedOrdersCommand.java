package by.epam.javatraining.restautant.command.impl;

import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.OrderService;



public class ViewUnconfirmedOrdersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewUnconfirmedOrdersCommand.class);
    private static final int UNCONFIRMED_ORDER_STATUS_ID = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService service = ServiceFactory.INSTANCE.getOrderService();
        List<Order> orderList = null;
        List<Order> unconfirmedOrderList = null;

        try {
            orderList = service.getAllOrders();
            unconfirmedOrderList = getUnconfirmedOrdersList(orderList);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.UNCONFIRMED_ORDERS.getValue(), unconfirmedOrderList);

        return PageType.ADMIN_UNCONFIRMED_ORDERS_PAGE.getValue();
    }

    private List<Order> getUnconfirmedOrdersList(List<Order> orderList) {
        List<Order> unconfirmedOrderList = new LinkedList<>();

        for (Order order : orderList) {
            if (order.getOrderStatusId() == UNCONFIRMED_ORDER_STATUS_ID) {
                unconfirmedOrderList.add(order);
            }
        }

        return unconfirmedOrderList;
    }
}
