package by.epam.javatraining.restautant.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.dao.impl.ItemOrderDAOImpl;
import by.epam.javatraining.restautant.entity.ItemOrder;
import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.service.ItemOrderService;

public class ItemOrderServiceImpl implements ItemOrderService {
    private static final Logger LOGGER = LogManager.getLogger(ItemOrderServiceImpl.class);

    private ItemOrderDAOImpl dao = ItemOrderDAOImpl.getInstance();

    private ItemOrderServiceImpl() {
    }

    private static class ItemOrderServiceImplHolder {
        private static final ItemOrderServiceImpl INSTANCE = new ItemOrderServiceImpl();
    }

    public static ItemOrderServiceImpl getInstance() {
        return ItemOrderServiceImplHolder.INSTANCE;
    }

    @Override
    public void createItemOrder(Map<Position, Integer> positionIntegerMap, Order order) throws ServiceException {
        ItemOrder itemOrder;

        for (Map.Entry<Position, Integer> entry : positionIntegerMap.entrySet()) {
            itemOrder = new ItemOrder();
            itemOrder.setPosition(entry.getKey());
            itemOrder.setQuantity(entry.getValue());

            try {
                dao.createItemOrderByOrder(itemOrder, order);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<ItemOrder> getItemOrdersByOrderId(int orderId) throws ServiceException {
        List<ItemOrder> itemOrderList;

        try {
            itemOrderList = dao.getItemOrdersByOrderId(orderId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return itemOrderList;
    }
}
