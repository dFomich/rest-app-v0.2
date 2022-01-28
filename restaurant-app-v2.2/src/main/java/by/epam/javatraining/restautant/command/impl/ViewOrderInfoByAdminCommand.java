package by.epam.javatraining.restautant.command.impl;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.command.PageType;
import by.epam.javatraining.restautant.entity.ItemOrder;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.ItemOrderService;



public class ViewOrderInfoByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewOrderInfoByAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<ItemOrder> itemOrderList = null;
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        ItemOrderService service = ServiceFactory.INSTANCE.getItemOrderService();

        try {
            itemOrderList = service.getItemOrdersByOrderId(orderId);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        request.setAttribute(JSPParameter.ITEM_ORDER_LIST.getValue(), itemOrderList);

        return PageType.ADMIN_ORDER_INFO.getValue();
    }
}
