package by.epam.javatraining.restautant.dao;

import java.util.List;

import by.epam.javatraining.restautant.entity.DeliveryAddress;
import by.epam.javatraining.restautant.exception.DAOException;





public interface DeliveryAddressDAO extends ModelDAO<DeliveryAddress> {

    @Override
    void create(DeliveryAddress deliveryAddress) throws DAOException;

    @Override
    void update(DeliveryAddress deliveryAddress) throws DAOException;

    @Override
    void delete(DeliveryAddress deliveryAddress) throws DAOException;

    @Override
    List<DeliveryAddress> getAll() throws DAOException;

    DeliveryAddress readById(int id) throws DAOException;
}
