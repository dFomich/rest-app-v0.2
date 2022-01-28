package by.epam.javatraining.restautant.factory;

import by.epam.javatraining.restautant.service.DeliveryAddressService;
import by.epam.javatraining.restautant.service.ItemOrderService;
import by.epam.javatraining.restautant.service.OrderService;
import by.epam.javatraining.restautant.service.PositionService;
import by.epam.javatraining.restautant.service.UserService;
import by.epam.javatraining.restautant.service.impl.DeliveryAddressServiceImpl;
import by.epam.javatraining.restautant.service.impl.ItemOrderServiceImpl;
import by.epam.javatraining.restautant.service.impl.OrderServiceImpl;
import by.epam.javatraining.restautant.service.impl.PositionServiceImpl;
import by.epam.javatraining.restautant.service.impl.UserServiceImpl;

public enum ServiceFactory {

    INSTANCE;

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public OrderService getOrderService() {
        return OrderServiceImpl.getInstance();
    }

    public PositionService getPositionService() { return PositionServiceImpl.getInstance(); }

    public DeliveryAddressService getDeliveryAddressService() { return DeliveryAddressServiceImpl.getInstance(); }

    public ItemOrderService getItemOrderService() {
        return ItemOrderServiceImpl.getInstance();
    }
}
