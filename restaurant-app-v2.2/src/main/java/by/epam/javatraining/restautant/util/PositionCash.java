package by.epam.javatraining.restautant.util;

import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.exception.PositionInitializeException;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.PositionService;


public class PositionCash {
    private static final Logger LOGGER = LogManager.getLogger(PositionCash.class);

    private List<Position> positionList = new LinkedList<>();

    private PositionCash() {
    }

    private static class PositionCashHolder {
        private static final PositionCash INSTANCE = new PositionCash();
    }

    public static PositionCash getInstance() {
        return PositionCashHolder.INSTANCE;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positions) {
        positionList = positions;
    }

    public Position getPositionById(int id) {
        Position position = null;

        for (Position item: positionList) {
            if (item.getPositionId() == id) {
                position = item;
            }
        }

        return position;
    }

    public void initPositions() throws PositionInitializeException {
        PositionService service = ServiceFactory.INSTANCE.getPositionService();
        try {
            positionList = service.getAllPositions();
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new PositionInitializeException(e);
        }
    }
}
