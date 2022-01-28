package by.epam.javatraining.restautant.factory;

import by.epam.javatraining.restautant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restautant.dao.ItemOrderDAO;
import by.epam.javatraining.restautant.dao.OrderDAO;
import by.epam.javatraining.restautant.dao.PositionDAO;
import by.epam.javatraining.restautant.dao.UserDAO;

public interface DAOFactory {

    UserDAO getUserDAO();

    OrderDAO getOrderDAO();

    PositionDAO getPositionDAO();

    ItemOrderDAO getItemOrderDAO();

    DeliveryAddressDAO getDeliveryAddressDAO();
}
