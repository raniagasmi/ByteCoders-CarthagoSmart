package tn.esprit.bytecoders.Services;



import tn.esprit.bytecoders.Entity.Facture;
import tn.esprit.bytecoders.Entity.User;
import tn.esprit.bytecoders.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {
    private Connection cnx;

    public UserService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void lireFactureParNumero() {

    }

    public void supprimerFacture(User user, Facture facture) {
        String req = "DELETE FROM facture WHERE id_facture=? AND id_user=? AND estPayee=true";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
            //stm.setInt(1, user.getId_facture());
            stm.setInt(2, user.getId_user());


            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }


}


