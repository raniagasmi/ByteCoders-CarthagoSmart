package tn.esprit.bytecoders.Services;



import tn.esprit.bytecoders.Entity.Facture;
import tn.esprit.bytecoders.Entity.TypeFacture;
import tn.esprit.bytecoders.interfaces.IFacture;
import tn.esprit.bytecoders.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturesService implements IFacture {

    Connection connection;

    public FacturesService() {
        connection = MyDB.getInstance().getCnx();
    }

    @Override
    public void create(Facture fact) throws SQLException {
        try {
            String req = "INSERT INTO facture (libelle, date, date_ech, montant, type, estPayee) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, fact.getLibelle());
            preparedStatement.setDate(2, fact.getDate());
            preparedStatement.setDate(3, fact.getDate_ech());
            preparedStatement.setDouble(4, fact.getMontant());
            preparedStatement.setString(5, fact.getType().toString());
            preparedStatement.setBoolean(6, fact.isEstPayee());
            preparedStatement.executeUpdate();
            System.out.println("Facture ajoutée avec succès!");



        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Facture> getAll() throws SQLException {
        List<Facture> factures = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM facture");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Facture facture = new Facture();
                facture.setRef_facture(rs.getInt("id_facture"));
                facture.setLibelle(rs.getString("libelle"));
                facture.setDate(rs.getDate("date"));
                facture.setDate_ech(rs.getDate("date_ech"));
                facture.setMontant(rs.getDouble("montant"));
                if (rs.getString("type").equals("EAU")) {
                    facture.setType(TypeFacture.EAU);
                } else {
                    facture.setType(TypeFacture.ENERGY);
                }
                facture.setEstPayee(rs.getBoolean("estPayee"));
                factures.add(facture);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return factures;
    }


    @Override
    public void update(Facture facture) {
        try {
            String req = "UPDATE facture SET libelle = ?, date = ?, date_ech = ?, montant = ?, type = ?, estPayee = ? WHERE id_facture = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, facture.getLibelle());
            preparedStatement.setDate(2, facture.getDate());
            preparedStatement.setDate(3, facture.getDate_ech());
            preparedStatement.setDouble(4, facture.getMontant());
            preparedStatement.setString(5, facture.getType().toString());
            preparedStatement.setBoolean(6, facture.isEstPayee());
            preparedStatement.setInt(7, facture.getRef_facture());
            preparedStatement.executeUpdate();
            System.out.println("Facture modifiée avec succès!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Facture facture) {
        try {
            String req = "DELETE FROM facture WHERE id_facture = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, facture.getRef_facture());
            preparedStatement.executeUpdate();
            System.out.println("Facture supprimée avec succès!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Facture getFactureById(int id) {
        String req = "SELECT * FROM facture WHERE id_facture = ?";
        Facture facture = new Facture();
        try {
            PreparedStatement stm = connection.prepareStatement(req);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                facture.setRef_facture(rs.getInt("id_facture"));
                facture.setLibelle(rs.getString("libelle"));
                facture.setDate(rs.getDate("date"));
                facture.setDate_ech(rs.getDate("date_ech"));
                facture.setMontant(rs.getDouble("montant"));
                if (rs.getString("type").equals("EAU")) {
                    facture.setType(TypeFacture.EAU);
                } else {
                    facture.setType(TypeFacture.ENERGY);
                }
                facture.setEstPayee(rs.getBoolean("estPayee"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facture;
    }
}