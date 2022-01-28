package by.epam.javatraining.restautant.dao.impl;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.javatraining.restautant.builder.UserBuilder;
import by.epam.javatraining.restautant.dao.DBFields;
import by.epam.javatraining.restautant.dao.SQLQuery;
import by.epam.javatraining.restautant.dao.UserDAO;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.pool.ConnectionPool;



public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private static final String DAO_ISSUES_MESSAGE = "Issues with connecting to database!";

    private ConnectionPool pool = ConnectionPool.getInstance();

    private UserDAOImpl() {
    }

    private static class UserDAOImplHolder {
        private static final UserDAOImpl INSTANCE = new UserDAOImpl();
    }

    public static UserDAOImpl getInstance() {
        return UserDAOImplHolder.INSTANCE;
    }

    @Override
    public User readByLogin(String login) throws DAOException {
        User user = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.READ_USER_BY_LOGIN_QUERY.getValue())) {
            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = buildUser(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public User readById(int id) throws DAOException {
        User user = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.READ_USER_BY_ID.getValue())) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = buildUser(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public User readByEmail(String email) throws DAOException {
        User user = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.READ_USER_BY_EMAIL.getValue())) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = buildUser(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public void deleteUserById(int userId) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_USER_BY_ID.getValue())) {
            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }
    }

    @Override
    public void create(User user) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_USER.getValue())) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setInt(7, user.getRole().getRoleId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_USER.getValue())) {

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setInt(4, user.getUserId());
            statement.setString(5, user.getLogin());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_USER.getValue())) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> userList = new ArrayList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_USERS.getValue())) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = buildUser(resultSet);
                    userList.add(user);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(DAO_ISSUES_MESSAGE, e);
            throw new DAOException(e);
        }

        return userList;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .buildId(resultSet.getInt(DBFields.DB_USER_ID.getValue()))
                .buildLogin(resultSet.getString(DBFields.DB_USER_LOGIN.getValue()))
                .buildPassword(resultSet.getString(DBFields.DB_USER_PASSWORD.getValue()))
                .buildEmail(resultSet.getString(DBFields.DB_USER_EMAIL.getValue()))
                .buildPhoneNumber(resultSet.getString(DBFields.DB_USER_PHONE_NUMBER.getValue()))
                .buildFirstName(resultSet.getString(DBFields.DB_USER_FIRST_NAME.getValue()))
                .buildLastName(resultSet.getString(DBFields.DB_USER_LAST_NAME.getValue()))
                .buildRole(resultSet.getInt(DBFields.DB_USER_ROLE_ID.getValue()))
                .build();
    }
}
