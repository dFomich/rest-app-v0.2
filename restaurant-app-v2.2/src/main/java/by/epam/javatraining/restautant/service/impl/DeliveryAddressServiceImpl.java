package by.epam.javatraining.restautant.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.dao.DeliveryAddressDAO;
import by.epam.javatraining.restautant.entity.DeliveryAddress;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.DAOFactoryImpl;
import by.epam.javatraining.restautant.service.DeliveryAddressService;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private static final Logger LOGGER = LogManager.getLogger(DeliveryAddressServiceImpl.class);

    private DeliveryAddressDAO dao = DAOFactoryImpl.INSTANCE.getDeliveryAddressDAO();

    private DeliveryAddressServiceImpl() {
    }

    private static class DeliveryAddressServiceImplHolder {
        private static final DeliveryAddressServiceImpl INSTANCE = new DeliveryAddressServiceImpl();
    }

    public static DeliveryAddressServiceImpl getInstance() {
        return DeliveryAddressServiceImplHolder.INSTANCE;
    }

    @Override
    public void createDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException {
        try {
            if (!isAddressExist(deliveryAddress)) {
                dao.create(deliveryAddress);
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException {
        try {
            dao.update(deliveryAddress);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteDeliveryAddress(DeliveryAddress deliveryAddress) throws ServiceException {
        try {
            dao.delete(deliveryAddress);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public DeliveryAddress readDeliveryAddressById(int id) throws ServiceException {
        DeliveryAddress deliveryAddress;

        try {
            deliveryAddress = dao.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return deliveryAddress;
    }

    @Override
    public List<DeliveryAddress> getAllAddresses() throws ServiceException {
        List<DeliveryAddress> addressList;
        try {
            addressList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return addressList;
    }

    private boolean isAddressExist(DeliveryAddress deliveryAddress) throws DAOException {
        List<DeliveryAddress> deliveryAddressList = dao.getAll();

        return deliveryAddressList.contains(deliveryAddress);
    }
}
