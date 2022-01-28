package by.java.epam.javatraining.service.impl;


import org.junit.Test;

import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.factory.ServiceFactory;
import by.epam.javatraining.restautant.service.UserService;

public class UserServiceImplTest {
    private UserService service = ServiceFactory.INSTANCE.getUserService();

    @Test(expected = ServiceException.class)
    public void testRegisterUserWithNullUserShouldThrowServiceException() throws ServiceException {
        User user = null;
        service.registerUser(user);
    }

    @Test(expected = ServiceException.class)
    public void testRegisterUserWithNotValidParametersShouldThrowServiceException() throws ServiceException {
        User user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375292999615");
        user.setEmail("notvalid@gmail");
        service.registerUser(user);
    }
}
