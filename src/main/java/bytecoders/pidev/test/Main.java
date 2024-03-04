package bytecoders.pidev.test;

import bytecoders.pidev.models.Roles;
import bytecoders.pidev.models.User;
import bytecoders.pidev.services.Encryption;
import bytecoders.pidev.services.UserServices;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

     //  UserServices us = new UserServices();
        UserServices us = new UserServices();

        //************ajouter*******************


       /* String password ="rania";
        password = Encryption.encrypt(password);
        System.out.println(password);

        User u = new User(123,"montassar","kabsi","newUser",123,"montassar.kabsi@esprit.tn",password, Roles.MEMBRE,"/image.png");
        System.out.println(u.getMdp());

        us.addUser(u);*/




        //************modifier************
       /* String password ="newnew";
        password = Encryption.encrypt(password);

        try {
          //  us.updateUser(new User(9,3,"ranran",123456, "ranouch.gasmi", "ranouch", "rania123.png", Roles.ORGANISATEURS));
           us.updateUser(new User());

        }catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
*/
        //************supprimer************
       /* try {
            us.deleteUser(7);
        }catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }*/


        //************afficher************
      /* try {
            System.out.println(us.recuperer());
        }catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }*/
    }

}
