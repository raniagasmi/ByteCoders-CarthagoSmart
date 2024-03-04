package ByteCoders.Service;

import java.sql.*;

import ByteCoders.Service.MyDB;
import ByteCoders.Service.Collect;
import  java.sql.Connection;
import java.util.List;
import java.sql.SQLException;
import ByteCoders.Model.collect;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class CollectDechet implements Collect<collect> {
    private Connection cnx;

    public CollectDechet() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void addDechets(collect Dechets) throws SQLException {
        String req = "INSERT INTO collectdechets (`nomType`, `categorie`,`PointRamassage`, `DateRamassage`,`PointRecyclage`) VALUES (?, ? , ? , ? , ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            //stm.setInt(1,typeDechets.getTypeId());
            stm.setString(1, Dechets.getNomType());
            stm.setString(2, Dechets.getCategorie().toString());
            stm.setString(3, Dechets.getPointRamassage());
            stm.setString(4, Dechets.getDateRamassage());
            stm.setString(5,Dechets.getPointRecyclage());
            stm.executeUpdate();
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());

        }
    }
    public void deleteDechets(int typeId) throws SQLException {
        String req = "DELETE FROM collectdechets WHERE typeId = ?";
        PreparedStatement stm = cnx.prepareStatement(req);
        stm.setInt(1, typeId);
        stm.executeUpdate();
    }

    public void updateDechets(collect Dechets) throws SQLException {
        String req = "UPDATE collectdechets SET nomType = ?, categorie = ? WHERE typeId = ?";

        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, Dechets.getNomType());
            stm.setString(2, Dechets.getCategorie().toString());
            stm.setInt(3,Dechets.getTypeId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public collect getTypeDechetsById(int typeId) throws SQLException {
        String req = "SELECT * FROM collectdechets WHERE typeId = ?";

        PreparedStatement stm = cnx.prepareStatement(req);
        stm.setInt(1, typeId);

        ResultSet resultSet = stm.executeQuery();
        return null;
    }

    /*public void EnvoieMail(String userEmail,MimeMessage message) {
        // Définir les propriétés SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Créer une session SMTP
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sarra.rejeb@esprit.tn", "Sadokhou2212@@");
            }
        });

        try {
            // Créer et configurer le message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sarra.rejeb@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Date de collecte incorrecte");


            // Envoyer le message
            Transport.send(message);
            System.out.println("Message envoyé avec succès à " + userEmail);
            //System.out.println("Code de confirmation stocké dans la base de données pour " + userEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
        }


    }






    /*public void addCollecte(collect collect) throws SQLException {
        String req = "INSERT INTO collectdechets (`adresse`, `date`,`jour_ramassage`) VALUES (?, ?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            stm.setString(1, collect.getAdresse());
            stm.setString(2, collect.getDate());
            stm.setBoolean(3,collect.isJourRamassage());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/



}

    /*public collect getTypeDechetsById(int typeId) throws SQLException {
        String req = "SELECT * FROM typesdechets WHERE typeId = ?";

        try (PreparedStatement stm = cnx.prepareStatement(req)) {
            stm.setInt(1, typeId);

            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToTypeDechets(resultSet);
            }
        }

        return null;
    }*/



    /*public void addLieuCollecte(collect collect) throws SQLException {
        String req = "INSERT INTO collectdechets (adresse) VALUES (?, ?)";

        PreparedStatement stm = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, collect.getAdresse());

            stm.executeUpdate();
    }*/
