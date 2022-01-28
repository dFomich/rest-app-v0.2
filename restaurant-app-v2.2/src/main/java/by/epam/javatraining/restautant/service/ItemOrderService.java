package by.epam.javatraining.restautant.service;

import java.util.List;
import java.util.Map;

import by.epam.javatraining.restautant.entity.ItemOrder;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.exception.ServiceException;



public interface ItemOrderService {

    void createItemOrder(Map<Position, Integer> positionIntegerMap , Order order) throws ServiceException;

    List<ItemOrder> getItemOrdersByOrderId(int orderId) throws ServiceException;
}
