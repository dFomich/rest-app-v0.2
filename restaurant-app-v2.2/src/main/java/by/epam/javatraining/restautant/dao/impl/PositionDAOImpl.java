package by.epam.javatraining.restautant.dao.impl;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import by.epam.javatraining.restautant.builder.PositionBuilder;
import by.epam.javatraining.restautant.dao.DBFields;
import by.epam.javatraining.restautant.dao.PositionDAO;
import by.epam.javatraining.restautant.dao.SQLQuery;
import by.epam.javatraining.restautant.entity.Position;
import by.epam.javatraining.restautant.exception.DAOException;
import by.epam.javatraining.restautant.pool.ConnectionPool;
import by.epam.javatraining.restautant.util.ImageEncoder;


public class PositionDAOImpl implements PositionDAO {
    private static final Logger LOGGER = LogManager.getLogger(PositionDAOImpl.class);

    private ConnectionPool pool = ConnectionPool.getInstance();

    private PositionDAOImpl() {
    }

    private static class PositionDAOImplHolder {
        private static final PositionDAOImpl INSTANCE = new PositionDAOImpl();
    }

    public static PositionDAOImpl getInstance() {
        return PositionDAOImplHolder.INSTANCE;
    }

    @Override
    public void create(Position position) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.CREATE_POSITION.getValue())) {

            statement.setString(1, position.getItemName());
            statement.setInt(2, position.getGroup().getGroupId());
            statement.setBigDecimal(3, position.getItemPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Position position) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UPDATE_POSITION.getValue())) {

            statement.setString(1, position.getItemName());
            statement.setInt(2, position.getGroup().getGroupId());
            statement.setBigDecimal(3, position.getItemPrice());
            statement.setInt(4, position.getPositionId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Position position) throws DAOException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.DELETE_POSITION.getValue())) {

            statement.setInt(1, position.getPositionId());
            statement.setString(2, position.getItemName());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
    }

    @Override
    public Position readById(int id) throws DAOException {
        Position position = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.READ_POSITION_BY_ID.getValue())) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    position = buildPosition(resultSet);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return position;
    }

    @Override
    public List<Position> getAll() throws DAOException {
        List<Position> positionList = new ArrayList<>();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GET_ALL_POSITIONS.getValue())) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Position position = buildPosition(resultSet);
                    positionList.add(position);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }

        return positionList;
    }

    private Position buildPosition(ResultSet resultSet) throws SQLException {
        ImageEncoder encoder = ImageEncoder.getInstance();

        return new PositionBuilder()
                .buildId(resultSet.getInt(DBFields.DB_POSITIONS_ITEM_ID.getValue()))
                .buildItemName(resultSet.getString(DBFields.DB_POSITIONS_ITEM_NAME.getValue()))
                .buildItemPrice(resultSet.getBigDecimal(DBFields.DB_POSITIONS_ITEM_PRICE.getValue()))
                .buildPositionImage(encoder.encodeImageToBase64String(resultSet
                .getBlob(DBFields.DB_POSITION_ITEM_IMAGE.getValue())))
                .buildPositionItemGroup(resultSet.getInt(DBFields.DB_POSITIONS_GROUP_ID.getValue()))
                .build();
    }
}
