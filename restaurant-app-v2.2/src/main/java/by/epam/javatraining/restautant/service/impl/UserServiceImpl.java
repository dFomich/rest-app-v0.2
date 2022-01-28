package by.epam.javatraining.restautant.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.javatraining.restautant.dao.UserDAO;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.exception.ServiceException;
import by.epam.javatraining.restautant.exception.UtilException;
import by.epam.javatraining.restautant.factory.DAOFactoryImpl;
import by.epam.javatraining.restautant.service.UserService;
import by.epam.javatraining.restautant.util.PasswordUtils;
import by.epam.javatraining.restautant.validator.UserValidator;



public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private UserDAO dao = DAOFactoryImpl.INSTANCE.getUserDAO();

    private UserServiceImpl() {
    }

    private static class UserServiceImplHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.INSTANCE;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user = null;

        if (UserValidator.INSTANCE.validateLogin(login) && UserValidator.INSTANCE.validatePassword(password)) {
            user = getUserByLogin(login);

            try {
                if (user == null || !PasswordUtils.getInstance().verifyUserPassword(password, user.getPassword())) {
                    throw new ServiceException("Wrong password or user does not exist!");
                }
            } catch (UtilException e) {
                throw new ServiceException(e);
            }
        }

        return user;
    }

    @Override
    public void registerUser(User user) throws ServiceException {
        if (!UserValidator.INSTANCE.validateUser(user)) {
            LOGGER.warn("User parameters set incorrectly");
            throw new ServiceException("Can't register user. User parameters set incorrectly");
        }

        if (isLoginExist(user.getLogin())) {
            LOGGER.warn("Login is Used");
            throw new ServiceException("Can't register user with this login. Login is used!");
        }

        if (isEmailExist(user.getEmail())) {
            LOGGER.warn("Email is used");
            throw new ServiceException("Can't register user with this email. Email is used!");
        }

       hashPassword(user);

        try {
            dao.create(user);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> userList = null;

        try {
            userList = dao.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return userList;
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        if (!UserValidator.INSTANCE.validateUser(user)) {
            LOGGER.warn("User parameters are incorrect!");
            throw new ServiceException("Can't delete user!");
        }

        try {
            dao.delete(user);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public User getUserByLogin(String login) throws ServiceException {
        User user;

        try {
            user = dao.readByLogin(login);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        User user;

        try {
            user = dao.readByEmail(email);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        User user;

        try {
            user = dao.readById(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public void deleteUserById(int userId) throws ServiceException {
        try {
            dao.deleteUserById(userId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    private boolean isLoginExist(String login) throws ServiceException {
        User user;

        try {
            user = dao.readByLogin(login);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }

        return user != null;
    }

    private boolean isEmailExist(String email) throws ServiceException {
        User user;

        try {
            user = dao.readByEmail(email);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }

        return user != null;
    }

    private void hashPassword(User user) throws ServiceException {
        try {
            user.setPassword(PasswordUtils.getInstance().generateSecuredPassword(user.getPassword()));
        } catch (UtilException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
