package ByteCoders.Service;



import ByteCoders.Model.Facture;
import ByteCoders.Model.UserMolka;

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

    public void supprimerFacture(UserMolka user, Facture facture) {
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


