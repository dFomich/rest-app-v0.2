package by.epam.javatraining.restautant.service;

import java.util.List;

import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.exception.ServiceException;


public interface PositionService {

    void createPosition(Position position) throws ServiceException;

    void deletePosition(Position position) throws ServiceException;

    Position getPositionById(int id) throws ServiceException;

    List<Position> getAllPositions() throws ServiceException;

    void updatePrice(Position position) throws ServiceException;
}
