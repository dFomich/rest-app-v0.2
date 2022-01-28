package by.epam.javatraining.restautant.service;

import java.util.List;

import by.epam.javatraining.restautant.entity.Order;
import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;

public interface OrderService {

    /**
     * Create {@link Order} with given parameters
     *
     * @param order {@link Order} must be created in dao
     * @throws ServiceException if an error occurs while creating objects
     */
    void createOrder(Order order) throws ServiceException;

    /**
     * Delete {@link Order}
     *
     * @param order {@link Order} must be deleted from dao
     * @throws ServiceException if an error occurs while deleting objects
     */
    void deleteOrder(Order order) throws ServiceException;

    /**
     * Get {@link Order} from dao  by parameter <tt>id<tt/>
     *
     * @param id parameter which equal or not {@link Order}'s id
     * @return {@link Order} from dao
     * @throws ServiceException if {@link Order} with <tt>id<tt/> does not exist or error
     * while reading object from dao
     */
    Order getOrderById(int id) throws ServiceException;

    /**
     * Get all {@link Order}'s from dao
     *
     * @return List of {@link Order}'s
     * @throws ServiceException if error while occurs while reading object from dao
     */
    List<Order> getAllOrders() throws ServiceException;

    /**
     * Get all {@link Order}'s from dao by {@link User}'s id
     *
     * @param userId {@link User}'s id
     * @return List of {@link Order}'s where {@link User}'s id equal userId
     * @throws ServiceException if an error occurs while reading object from dao
     */
    List<Order> getAllOrdersByUserId(int userId) throws ServiceException;

    /**
     * Update {@link Order} from dao
     *
     * @param order {@link Order} which must be updated in dao
     * @throws ServiceException  if <tt>order<tt/> does not exist or
     * if an error occurs while updating object from dao
     */
    void updateOrder(Order order) throws ServiceException;

    /**
     * Delete {@link Order} from dao where {@link Order}'s id equal <tt>orderId<tt/>
     *
     * @param orderId
     * @throws ServiceException if {@link Order} with <tt>orderId<tt/> does not exist
     * or if an error occurs while deleting object from dao
     */
    void deleteOrderById(int orderId) throws ServiceException;
}
