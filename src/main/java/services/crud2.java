package services;

import models.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface crud2 {
    void ajouter1(Reservation reservation) throws SQLException;

    void supprimer1(Reservation reservation) throws SQLException;

    void modifier1(Reservation reservation) throws SQLException;

    List<Reservation> getAll1() throws SQLException;

    Reservation getById1(int id) throws SQLException;
}
