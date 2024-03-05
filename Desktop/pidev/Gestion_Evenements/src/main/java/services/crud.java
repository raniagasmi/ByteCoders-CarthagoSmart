package services;

import models.Event;

import java.sql.SQLException;
import java.util.List;


public interface crud<T> {

    void ajouter(T t) throws SQLException;

    void supprimer(T t) throws SQLException;




    void modifier(Event event) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id) throws  SQLException;
}

