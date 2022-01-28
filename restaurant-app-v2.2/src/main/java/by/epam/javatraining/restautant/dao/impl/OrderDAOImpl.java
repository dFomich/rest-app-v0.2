package by.epam.javatraining.restautant.dao.impl;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import by.epam.javatraining.restautant.builder.OrderBuilder;
import by.epam.javatraining.restautant.dao.DBFields;
import by.epam.javatraining.restautant.dao.OrderDAO;
import by.epam.javatraining.restautant.dao.SQLQuery;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.pool.ConnectionPool;



public class OrderDAOImpl implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);

    private ConnectionPool pool = ConnectionPool.getInstance();

    private OrderDAOImpl() {
    }

    private static class OrderDAOHolder {
        private static final OrderDAOImpl INSTANCE = new OrderDAOImpl();
    }

    public static OrderDAOImpl getInstance() {
        return OrderDAOHolder.INSTANCE;
    }

    @Override
    public void create(Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_ORDER.getValue())) {

            statement.setDate(1, new Date(new java.util.Date().getTime()));
            statement.setInt(2, order.getCustomerId());
            statement.setBigDecimal(3, order.getTotalPrice());
            statement.setInt(4, order.getOrderStatusId());
            statement.setString(5, order.getDeliveryAddress().getStreet());
            statement.setInt(6, order.getDeliveryAddress().getBuildNumber());
            statement.setInt(7, order.getDeliveryAddress().getApartmentNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_ORDER.getValue())) {

            statement.setBigDecimal(1, order.getTotalPrice());
            statement.setInt(2, order.getOrderStatusId());
            statement.setInt(3, order.getOrderId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Order order) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_ORDER.getValue())) {

            statement.setInt(1, order.getOrderId());
            statement.setInt(2, order.getCustomerId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public Order readById(int id) throws DAOException {
        Order order = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.READ_ORDER_BY_ID.getValue())) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    order = buildOrder(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return order;
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) throws DAOException {
        List<Order> orderList = new LinkedList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_ORDERS_BY_USER_ID.getValue())) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = buildOrder(resultSet);
                    orderList.add(order);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return orderList;
    }

    @Override
    public void deleteOrderById(int orderId) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_ORDER_BY_ORDER_ID.getValue())) {

            statement.setInt(1, orderId);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> getAll() throws DAOException {
        List<Order> orderList = new LinkedList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_ORDERS.getValue());
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Order order = buildOrder(resultSet);
                orderList.add(order);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return orderList;
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        return new OrderBuilder()
                .buildOrderId(resultSet.getInt(DBFields.DB_ORDER_ID.getValue()))
                .buildOrderDate(resultSet.getDate(DBFields.DB_ORDER_DATE.getValue()))
                .buildCustomerId(resultSet.getInt(DBFields.DB_ORDER_CUSTOMER_ID.getValue()))
                .buildTotalPrice(resultSet.getBigDecimal(DBFields.DB_ORDER_TOTAL_PRICE.getValue()))
                .buildOrderStatusId(resultSet.getInt(DBFields.DB_ORDER_STATUS.getValue()))
                .buildDeliveryAddress(resultSet.getInt(DBFields.DB_ORDER_DELIVERY_ADDRESS_ID.getValue()))
                .build();
    }
}
