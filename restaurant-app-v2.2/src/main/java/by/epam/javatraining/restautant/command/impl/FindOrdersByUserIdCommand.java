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
import by.epam.javatraining.restautant.validator.EnterParameterValidator;

public class FindOrdersByUserIdCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindOrdersByUserIdCommand.class);
    private static final String WRONG_NUMBER_MESSAGE = "Not a number";
    private static final String EMPTY_ORDER_MESSAGE = "There is no orders by this user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId;
        List<Order> orderList = null;
        OrderService service = ServiceFactory.INSTANCE.getOrderService();

        if (EnterParameterValidator.INSTANCE.isNumeric(
                request.getParameter(JSPParameter.ORDER_SEARCH_PARAMETER.getValue()))) {
            userId = Integer.parseInt(request.getParameter(JSPParameter.ORDER_SEARCH_PARAMETER.getValue()));

            try {
                orderList = service.getAllOrdersByUserId(userId);

                if (orderList.isEmpty()) {
                    request.setAttribute(JSPParameter.ORDER_SEARCH_MESSAGE.getValue(), EMPTY_ORDER_MESSAGE);
                }
            } catch (ServiceException e) {
                LOGGER.error(e);
            }

        } else {
            request.setAttribute(JSPParameter.ORDER_SEARCH_MESSAGE.getValue(), WRONG_NUMBER_MESSAGE);
        }

        request.setAttribute(JSPParameter.USER_ORDER_LIST.getValue(), orderList);

        return PageType.ADMIN_ORDER_SEARCH.getValue();
    }
}
