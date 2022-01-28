package by.epam.javatraining.restautant.service;

import java.util.List;

import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.exception.ServiceException;



public interface UserService {

    /**
     * Signs in and return {@link User} with <tt>login</tt> and <tt>password</tt>.
     * Throws ServiceException if <tt>login</tt> or <tt>password</tt> is null or
     * empty or if <tt>login</tt> or <tt>password</tt> do not accords to specify
     * pattern. {@see by.epam.javatraining.restaurant.validator.UserValidator}
     * or if {@link User} with <tt>login</tt> and <tt>password</tt> do not present
     * into data source or if an error occurs while searching {@link User} into data
     * source
     *
     * @param login {@link User}'s login
     * @param password {@link User}'s password
     * @return {@link User} with give login and password from data source
     * @throws ServiceException if {@link User} with <tt>login</tt> and <tt>password</tt> do not present
     * into data source or if an error occurs while searching {@link User} into data source
     */
    User signIn(String login, String password) throws ServiceException;

    /**
     * Registers {@link User} with parameters. Throws ServiceException if <tt>login</tt>
     * or <tt>password</tt> or <tt>phoneNumber</tt> or <tt>email</tt> is null or empty or if <tt>login</tt>
     * or <tt>password</tt> etc. do not accords to specify pattern
     * {@see by.epam.javatraining.restaurant.validator.UserValidator} or if
     * user with <tt>login</tt> has already registered or if <tt>password</tt> and
     * <tt>repeatedPassword</tt> do not match or if an error occurs while writing
     * new {@link User} into data source
     *
     * @param user {@link User} to registration
     * @throws ServiceException if {@link User} does not registered or if an error occurs while writing {@link User}
     */
    void registerUser(User user) throws ServiceException;

    /**
     * Get all {@link User}'s from dao
     *
     * @return List of {@link User}'s from dao
     * @throws ServiceException if an error occurs while reading objects
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Delete {@link User}
     *
     * @param user {@link User} that must be deleted
     * @throws ServiceException if {@link User} equal null or error occurs while deleting object
     */
    void deleteUser(User user) throws ServiceException;

    /**
     * Get {@link User} by {@link User}'s login
     *
     * @param login parameter which must be equal or not with {@link User}'s login
     * @return {@link User} with equal parameter <tt>login</tt>
     * @throws ServiceException if login equal null or error occurs while reading object
     */
    User getUserByLogin(String login) throws ServiceException;

    /**
     *
     * Get {@link User} by {@link User}'s email
     *
     * @param email parameter which must be equal or not with {@link User}'s email
     * @return {@link User} with equal parameter <tt>email</tt>
     * @throws ServiceException if {@link User} does not exist or error occurs while reading object
     */
    User getUserByEmail(String email) throws ServiceException;

    /**
     * Get {@link User} by {@link User}'s ID
     *
     * @param id  parameter which must be equal or not with {@link User}'s ID
     * @return {@link User} with equal parameter <tt>id</tt>
     * @throws ServiceException if {@link User} does not exist or error occurs while reading object
     */
    User getUserById(int id) throws ServiceException;

    /**
     *
     * Delete {@link User} by {@link User}'s ID
     *
     * @param userId parameter  which must be equal or not with {@link User}'s ID for deleting
     * @throws ServiceException if {@link User} with <tt>userId</tt> does not exist or error occurs while deleting object
     */
    void deleteUserById(int userId) throws ServiceException;
}
