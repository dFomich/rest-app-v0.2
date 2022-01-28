package by.java.epam.javatraining.service.impl;



import java.math.BigDecimal;

import org.junit.Test;

import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.entity.PositionItemGroup;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.PositionService;

public class PositionServiceImplTest {
    private PositionService service = ServiceFactory.INSTANCE.getPositionService();

    @Test(expected = ServiceException.class)
    public void testCreatePositionWithNullEnterParameterShouldThrowServiceException() throws ServiceException {
        Position position = null;
        service.createPosition(position);
    }

    @Test(expected = ServiceException.class)
    public void testCreatePositionWithNotValidEnterParameterShouldThrowServiceException() throws ServiceException {
        Position position = new Position();
        position.setItemName("Pizza");
        position.setPositionId(10);
        PositionItemGroup group = new PositionItemGroup();
        group.setGroupId(1);
        position.setGroup(group);
        position.setItemPrice(new BigDecimal(-10));
        service.createPosition(position);
    }

    @Test(expected = ServiceException.class)
    public void testDeletePositionWithNullEnterParameterShouldThrowServiceException() throws ServiceException {
        Position position = null;
        service.deletePosition(position);
    }

    @Test(expected = ServiceException.class)
    public void testDeletePositionWithNotValidEnterParameterShouldThrowServiceException() throws ServiceException {
        Position position = new Position();
        position.setItemName("P");
        position.setPositionId(2);
        PositionItemGroup group = new PositionItemGroup();
        group.setGroupId(1);
        position.setGroup(group);
        position.setItemPrice(new BigDecimal(8));
        service.deletePosition(position);
    }

    @Test(expected = ServiceException.class)
    public void testGetPositionByIdMinusIdEnterParameterShouldThrowServiceException() throws ServiceException {
        int id = -5;

        service.getPositionById(id);
    }
}
