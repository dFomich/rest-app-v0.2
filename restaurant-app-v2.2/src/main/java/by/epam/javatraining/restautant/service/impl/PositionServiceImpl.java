package by.epam.javatraining.restautant.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.dao.PositionDAO;
import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.DAOFactoryImpl;
import by.epam.javatraining.restautant.service.PositionService;
import by.epam.javatraining.restautant.validator.PositionValidator;



public class PositionServiceImpl implements PositionService {
    private static final Logger LOGGER = LogManager.getLogger(PositionServiceImpl.class);

    private PositionDAO dao = DAOFactoryImpl.INSTANCE.getPositionDAO();

    private PositionServiceImpl() {
    }

    private static class PositionServiceImplHolder {
        private static final PositionServiceImpl INSTANCE = new PositionServiceImpl();
    }

    public static PositionServiceImpl getInstance() {
        return PositionServiceImplHolder.INSTANCE;
    }

    @Override
    public void createPosition(Position position) throws ServiceException {
        if (!PositionValidator.INSTANCE.isPositionValidate(position)) {
            throw new ServiceException("Position parameter set incorrectly!");
        }

        try {
            dao.create(position);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePosition(Position position) throws ServiceException {
        if (!PositionValidator.INSTANCE.isPositionValidate(position)) {
            throw new ServiceException("Position parameter set incorrectly!");
        }

        try {
            dao.delete(position);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Position getPositionById(int id) throws ServiceException {
        if (!PositionValidator.INSTANCE.isIdValidate(id)) {
            throw new ServiceException("Position id must be > 0");
        }

        Position position;

        try {
            position = dao.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return position;
    }

    @Override
    public List<Position> getAllPositions() throws ServiceException {
        List<Position> positionList;

        try {
            positionList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        return positionList;
    }

    @Override
    public void updatePrice(Position position) throws ServiceException {
        if (!PositionValidator.INSTANCE.isPositionValidate(position)) {
            throw new ServiceException("Position parameter set incorrectly!");
        }

        try {
            dao.update(position);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
