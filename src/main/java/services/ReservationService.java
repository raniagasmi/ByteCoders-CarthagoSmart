package services;

import models.Reservation;
import utils.MyDB;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
public class ReservationService implements crud2 {
    private static Connection connection;

    public ReservationService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter1(Reservation reservation) throws SQLException {
        String req = "INSERT INTO reservation (nom_event, email, numero, nbr_place) VALUES (?, ?, ?, ?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, reservation.getNom_event());
        pre.setString(2, reservation.getEmail());
        pre.setInt(3, reservation.getNumero());
        pre.setInt(4, reservation.getNbr_place());
        pre.executeUpdate();
    }

    @Override
    public void supprimer1(Reservation reservation) throws SQLException {
        String sql = "DELETE FROM reservation WHERE id_res = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, reservation.getId_res());
        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier1(Reservation reservation) throws SQLException {
        String sql = "UPDATE reservation SET nom_event=?, email=?, numero=?, nbr_place=? WHERE id_res=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reservation.getNom_event());
        preparedStatement.setString(2, reservation.getEmail());
        preparedStatement.setInt(3, reservation.getNumero());
        preparedStatement.setInt(4, reservation.getNbr_place());
        preparedStatement.setInt(5, reservation.getId_res());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Reservation> getAll1() throws SQLException {
        String sql = "SELECT * FROM reservation";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Reservation> reservations = new ArrayList<>();
        while (rs.next()) {
            Reservation r = new Reservation(
                    rs.getInt("id_res"),
                    rs.getString("nom_event"),
                    rs.getString("email"),
                    rs.getInt("numero"),
                    rs.getInt("nbr_place")
            );
            reservations.add(r);
        }
        return reservations;
    }

    @Override
    public Reservation getById1(int id) throws SQLException {
        String sql = "SELECT * FROM reservation WHERE id_res = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Reservation(
                    resultSet.getInt("id_res"),
                    resultSet.getString("nom_event"),
                    resultSet.getString("email"),
                    resultSet.getInt("numero"),
                    resultSet.getInt("nbr_place")
            );
        } else {
            return null;
        }
    }
}
