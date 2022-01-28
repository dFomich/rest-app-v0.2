package by.epam.javatraining.restautant.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.dao.OrderDAO;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.DAOFactoryImpl;
import by.epam.javatraining.restautant.service.OrderService;



public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    private OrderDAO dao = DAOFactoryImpl.INSTANCE.getOrderDAO();

    private OrderServiceImpl() {
    }

    private static class OrderServiceImplHolder {
        private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    }

    public static OrderServiceImpl getInstance() {
        return OrderServiceImplHolder.INSTANCE;
    }

    @Override
    public void createOrder(Order order) throws ServiceException {
        try {
            dao.create(order);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException {
        try {
            dao.delete(order);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Order getOrderById(int id) throws ServiceException {
        Order order;

        try {
            order = dao.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        List<Order> orderList;

        try {
            orderList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return orderList;
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) throws ServiceException {
        List<Order> orderList;

        try {
            orderList = dao.getAllOrdersByUserId(userId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return orderList;
    }

    @Override
    public void updateOrder(Order order) throws ServiceException {
        try {
            dao.update(order);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteOrderById(int orderId) throws ServiceException {
        try {
            dao.deleteOrderById(orderId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
