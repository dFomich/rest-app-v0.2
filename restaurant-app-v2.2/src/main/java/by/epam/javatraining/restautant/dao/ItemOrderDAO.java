package by.epam.javatraining.restautant.dao;

import java.util.List;

import by.epam.javatraining.restautant.entity.ItemOrder;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.exception.DAOException;

public interface ItemOrderDAO extends ModelDAO<ItemOrder> {

    @Override
    void create(ItemOrder itemOrder) throws DAOException;

    @Override
    void update(ItemOrder itemOrder) throws DAOException;

    @Override
    void delete(ItemOrder itemOrder) throws DAOException;

    @Override
    List<ItemOrder> getAll() throws DAOException;

    void createItemOrderByOrder(ItemOrder itemOrder, Order order) throws DAOException;

    ItemOrder readByOrderIdAndPositionId(int orderId, int positionId) throws DAOException;

    List<ItemOrder> getItemOrdersByOrderId(int orderId) throws DAOException;
}
