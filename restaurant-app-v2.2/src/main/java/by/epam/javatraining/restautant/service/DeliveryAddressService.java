package by.epam.javatraining.restautant.service;

import java.util.List;

import by.epam.javatraining.restautant.entity.DeliveryAddress;
import by.epam.javatraining.restautant.exception.ServiceException;



public interface DeliveryAddressService {

    void createDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException;

    void updateDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException;

    void deleteDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException;

    DeliveryAddress readDeliveryAddressById(int id) throws ServiceException;

    List<DeliveryAddress> getAllAddresses() throws ServiceException;
}