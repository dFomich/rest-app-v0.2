package by.epam.javatraining.restautant.dao;

import java.util.List;

import by.epam.javatraining.restautant.exception.DAOException;



public interface ModelDAO <T> {

    void create(T t) throws DAOException;

    void update(T t) throws DAOException;

    void delete(T t) throws DAOException;

    List<T> getAll() throws DAOException;
}