package by.epam.javatraining.restautant.factory;

import by.epam.javatraining.restautant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restautant.dao.ItemOrderDAO;
import by.epam.javatraining.restautant.dao.OrderDAO;
import by.epam.javatraining.restautant.dao.PositionDAO;
import by.epam.javatraining.restautant.dao.UserDAO;
import by.epam.javatraining.restautant.dao.impl.DeliveryAddressDAOImpl;
import by.epam.javatraining.restautant.dao.impl.ItemOrderDAOImpl;
import by.epam.javatraining.restautant.dao.impl.OrderDAOImpl;
import by.epam.javatraining.restautant.dao.impl.PositionDAOImpl;
import by.epam.javatraining.restautant.dao.impl.UserDAOImpl;

public enum DAOFactoryImpl implements DAOFactory {

    INSTANCE;

    @Override
    public UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return OrderDAOImpl.getInstance();
    }

    @Override
    public PositionDAO getPositionDAO() { return PositionDAOImpl.getInstance(); }

    @Override
    public ItemOrderDAO getItemOrderDAO() {
        return ItemOrderDAOImpl.getInstance();
    }

    @Override
    public DeliveryAddressDAO getDeliveryAddressDAO() { return DeliveryAddressDAOImpl.getInstance(); }
}
