package by.epam.javatraining.restautant.dao;

import java.util.List;

import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.DAOException;

public interface OrderDAO extends ModelDAO<Order> {

    /**
     *  Add new {@link Order} to data source
     *
     * @param order {@link Order} to be added to data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void create(Order order) throws DAOException;

    /**
     *
     * Update {@link Order} from data source
     *
     * @param order {@link Order} to be updated into data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void update(Order order) throws DAOException;

    /**
     * Delete {@link Order} from data source
     *
     * @param order {@link Order} to be deleted from data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    void delete(Order order) throws DAOException;

    /**
     * Get all {@link Order}'s from data source
     *
     * @return {@link Order}'s from data source
     * @throws DAOException if a data source access error occurs
     */
    @Override
    List<Order> getAll() throws DAOException;

    /**
     * Get {@link Order} from data source with id equal <tt>id<tt>
     *
     * @param id parameter for searching {@link Order} coincide {@link Order}'s id
     * @return {@link Order} with equal to <tt>id<tt> parameter
     * @throws DAOException if a data source access error occurs
     */
    Order readById(int id) throws DAOException;

    /**
     * Get all {@link Order}'s with userId
     *
     * @param userId {@link User} parameter for searching {@link Order}'s
     * @return all {@link Order}'s with userId fields
     * @throws DAOException if a data source access error occurs
     */
    List<Order> getAllOrdersByUserId(int userId) throws DAOException;

    /**
     * Delete {@link Order} with id equal <tt>id<tt>
     *
     * @param id to deleting {@link Order}
     * @throws DAOException if a data source access error occurs
     */
    void deleteOrderById(int id) throws DAOException;
}

