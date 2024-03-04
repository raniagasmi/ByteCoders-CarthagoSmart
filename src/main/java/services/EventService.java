package services;



import models.Event;
import utils.MyDB;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class EventService implements crud<Event> {

    private static Connection connection;
    private int id;

    public EventService() {
        connection = MyDB.getInstance().getConnection();
    }


    public void ajouter(Event event) throws SQLException {
        String req = "INSERT INTO event (nom_event, categorie, date, heure, lieu, capacite, cout) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, event.getNom_event());
        pre.setString(2, event.getCategorie());
        pre.setString(3, event.getDate());
        pre.setString(4, event.getHeure());
        pre.setString(5, event.getLieu());
        pre.setInt(6, event.getCapacite());
        pre.setFloat(7, event.getCout());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(Event event) throws SQLException {
        String sql = "delete from event where id= ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,event.getId());
        preparedStatement.executeUpdate();
    }



    @Override
    public void modifier(Event event) throws SQLException {
        String sql = "update event set nom_event=?, categorie=?, date=?, heure=?, lieu=?, capacite=?, cout=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, event.getNom_event());
        preparedStatement.setString(2, event.getCategorie());
        preparedStatement.setString(3, event.getDate());
        preparedStatement.setString(4, event.getHeure());
        preparedStatement.setString(5, event.getLieu());
        preparedStatement.setInt(6, event.getCapacite());
        preparedStatement.setFloat(7, event.getCout());
        preparedStatement.setInt(8, event.getId());


        preparedStatement.executeUpdate();
    }





    @Override
    public List<Event> getAll() throws SQLException {
        String sql = "select * from event";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Event> events = new ArrayList<>();
        while (rs.next()) {
            Event e = new Event(
                    rs.getString("nom_event"),
                    rs.getString("categorie"),
                    rs.getString("date"),
                    rs.getString("heure"),
                    rs.getString("lieu"),
                    rs.getInt("capacite"),
                    rs.getFloat("cout")

            );
            events.add(e);
        }
        return events;
    }

    @Override
    public Event getById(int id) throws SQLException {
        String sql = "SELECT id, nom_event, categorie, date, heure, lieu, capacite, cout FROM event WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String nom_event = resultSet.getString("nom_event");
            String categorie = resultSet.getString("categorie");
            String date = resultSet.getString("date");
            String heure = resultSet.getString("heure");
            String lieu = resultSet.getString("lieu");
            int capacite = resultSet.getInt("capacite");
            float cout = resultSet.getFloat("cout");

            return new Event( nom_event, categorie, date, heure,lieu, capacite, cout);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            MyDB db = new MyDB();

            // Obtenir la connexion à la base de données en appelant la méthode getCnx()
            connection = db.getCnx();

            // Créez la requête SQL pour récupérer tous les événements
            String query = "SELECT * FROM event";
            statement = connection.prepareStatement(query);

            // Exécutez la requête SQL
            resultSet = statement.executeQuery();

            // Parcourez les résultats et créez des objets Event à partir des données
            while (resultSet.next()) {
                String nom_event = resultSet.getString("nom_event");
                String categorie = resultSet.getString("categorie");
                String date = resultSet.getString("date");
                String heure = resultSet.getString("heure");
                String lieu = resultSet.getString("lieu");
                int capacite = resultSet.getInt("capacite");
                float cout = resultSet.getFloat("cout");

                // Créez un objet Event avec les données récupérées
                Event event = new Event(nom_event, categorie, date, heure, lieu, capacite, cout);

                // Ajoutez l'événement à la liste
                events.add(event);
            }
        } catch (SQLException e) {
            // Gérez les erreurs de connexion ou d'exécution de la requête SQL
            e.printStackTrace();
            throw e;
        } finally {
            // Fermez les ressources (ResultSet, PreparedStatement et Connection)
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        // Retournez la liste des événements récupérés depuis la base de données
        return events;
    }
}

