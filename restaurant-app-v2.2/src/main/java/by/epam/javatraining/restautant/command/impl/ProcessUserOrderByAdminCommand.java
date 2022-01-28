package by.epam.javatraining.restautant.command.impl;



import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.javatraining.restautant.builder.BillBuilder;
import by.epam.javatraining.restautant.command.Command;
import by.epam.javatraining.restautant.command.JSPParameter;
import by.epam.javatraining.restautant.entity.Bill;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.OrderService;
import by.epam.javatraining.restautant.service.UserService;
import by.epam.javatraining.restautant.util.EmailSender;

public class ProcessUserOrderByAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ProcessUserOrderByAdminCommand.class);
    private static final int CONFIRMED_ORDER_STATUS_ID = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(JSPParameter.ID.getValue()));
        OrderService service = ServiceFactory.INSTANCE.getOrderService();

        HttpSession session = request.getSession();
        User administrator = (User) session.getAttribute(JSPParameter.USER.getValue());

        try {
            Order order = service.getOrderById(orderId);
            order.setOrderStatusId(CONFIRMED_ORDER_STATUS_ID);
            service.updateOrder(order);

            Bill bill = createBill(order, administrator);
            sendBillToEmail(bill, order);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return new ViewUnconfirmedOrdersCommand().execute(request, response);
    }

    private void sendBillToEmail(Bill bill, Order order) throws ServiceException {
        EmailSender sender = EmailSender.getInstance();
        UserService service = ServiceFactory.INSTANCE.getUserService();
        User user = service.getUserById(order.getCustomerId());

        sender.sendEmail(bill.toString(), user.getEmail());
    }

    private Bill createBill(Order order, User user) {
        return new BillBuilder()
                .buildOrder(order)
                .buildUser(user)
                .buildDate(new Date())
                .build();
    }
}
