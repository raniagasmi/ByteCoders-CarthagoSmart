package ByteCoders.Service;
import ByteCoders.Model.Facture;
import ByteCoders.Model.Paiement;
import ByteCoders.Service.IFacture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class PaiementService {
    Connection connection;

    public PaiementService() {
        // Create the database connection
        connection = MyDB.getInstance().getCnx();
    }



    public int getRefFacture() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int refFacture = 0;

        try {
            // Get the database connection from MyDB class
            connection = MyDB.getInstance().getCnx();

            // Prepare the SQL query to retrieve the reference of the invoice
            String query = "SELECT id_facture FROM facture WHERE estPayee = 0 ORDER BY date_ech";
            statement = connection.prepareStatement(query);

            // Execute the SQL query
            resultSet = statement.executeQuery();

            // Retrieve the reference of the invoice from the query result
            if (resultSet.next()) {
                refFacture = resultSet.getInt("id_facture");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            // No need to close the connection as it's managed by MyDB class
        }

        return refFacture;
    }
    // Create a payment
    public void createP(Paiement paiement) throws SQLException {
        try {
            // Get the database connection from MyDB class
            connection = MyDB.getInstance().getCnx();

            // Prepare the SQL query to insert a payment
            String query = "INSERT INTO paiement (id_facture, id_user, montant, mode_paiement) VALUES (16,1,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, paiement.getMontant());
            statement.setString(2, paiement.getMode_paiement());

            statement.executeUpdate();
            // Execute the SQL query
            System.out.println("Payment effectué avec succés!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            if (connection != null) {
                connection.close();
            }
        }

    }}