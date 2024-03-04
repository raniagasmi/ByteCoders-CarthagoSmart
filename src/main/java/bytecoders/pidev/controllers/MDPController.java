package bytecoders.pidev.controllers;

import bytecoders.pidev.models.User;
import bytecoders.pidev.services.Encryption;
import bytecoders.pidev.services.UserServices;
import bytecoders.pidev.utils.MyDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import bytecoders.pidev.controllers.cAlert;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class MDPController {

    public static String user;
    private static String userEmail;
    private User u = null;
    @FXML
    private PasswordField fxoldpassword;

    @FXML
    private PasswordField fxnewpassword;

    @FXML
    private Button UpdatePassbtn;


    // public static String MDPController.userEmail =;
    @FXML
    private TextField user_email;
    @FXML
    private Button submit;
    //private static String userEmail;

    @FXML
    private Button retour;


    @FXML
    private Button submit1;


    @FXML
    private TextField code;

    cAlert cAlert = new cAlert();
   // User u = null ;



    @FXML
    void initialize() {
        assert user_email != null : "fx:id=\"user_email\" was not injected: check your FXML file 'MDP.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'MDP.fxml'.";
        assert code != null : "fx:id=\"code\" was not injected: check your FXML file 'MDP.fxml'.";
        assert submit1 != null : "fx:id=\"submit1\" was not injected: check your FXML file 'MDP.fxml'.";

    }

    @FXML
    void UpdatePassword(ActionEvent event) throws Exception {

        String oldPassword = fxoldpassword.getText();
        String newPassword = fxnewpassword.getText();

       // User u = UserServices.getCurrentUtilisateur();
        if (verifyPassword(oldPassword, Encryption.encrypt(u.getMdp()))) {
            newPassword = hashPassword(newPassword);
            boolean updateResult = UserServices.updateUserNewPass(u.getAddEmail(), newPassword);
            if (updateResult) {
                Stage stage = (Stage) UpdatePassbtn.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Changement de mot de passe");
                alert.setHeaderText("Votre mot de passe a été modifié avec succès.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur s'est produite lors de la mise à jour du mot de passe.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("L'ancien mot de passe est incorrect.");
            alert.showAndWait();
        }
    }



    public String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt(13);
        return BCrypt.hashpw(plainPassword, salt);
    }

    public static boolean verifyPassword(String candidatePassword, String hashedPassword) {
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }


 /*  @FXML
    private void user_restore(ActionEvent event) throws SQLException, Exception {

        UserServices s = new UserServices();
        Connection connection = null;

        try {
            // Ouvrir une connexion à la base de données
            connection = MyDB.getInstance().getCnx();
            if (userEmail != null) {
            User a = s.recuperer().stream().filter(x -> userEmail.equals(x.getAddEmail())).findAny().orElse(null);
            if (a != null) {
                // Envoyer un e-mail à l'utilisateur
                s.EnvoieMail(a.getAddEmail());
                // Charger Code.fxml
                loadCodeFXML(event);

            } else {
                // Aucun utilisateur trouvé avec l'adresse e-mail donnée
                System.out.println("Aucun utilisateur trouvé avec l'adresse e-mail donnée.");
            }
            } else {
                // L'utilisateur n'a pas saisi d'adresse e-mail
                System.out.println("L'adresse e-mail de l'utilisateur est null.");
            }
        } catch (SQLException e) {
            // Gérer les exceptions SQL
            e.printStackTrace();
        } finally {
            // Fermeture de la connexion JDBC dans le bloc finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(userEmail);
    }
*/

    UserServices us = new UserServices();

    @FXML
    private void user_restore(ActionEvent event) throws SQLException, Exception {
        String userEmail = user_email.getText();
        MDPController.setUserEmail(userEmail);
        UserServices s = new UserServices();
        Connection connection = null;

        try {
            // Ouvrir une connexion à la base de données
            connection = MyDB.getInstance().getCnx();
            User a = s.recuperer().stream().filter(x -> userEmail.equals(x.getAddEmail())).findAny().orElse(null);

            if (a != null) {
            ///if (!us.userExist(userEmail)) {
                u = us.getByEmail(userEmail);
                // Envoyer un e-mail à l'utilisateur
                s.EnvoieMail(a.getAddEmail());
                // Charger Code.fxml
              //  loadCodeFXMfL(event);
            } else {
                // Aucun utilisateur trouvé avec l'adresse e-mail donnée
                cAlert.generateAlert("WARNING","Email n'existe pas");
            }
        } catch (SQLException e) {
            // Gérer les exceptions SQL
            e.printStackTrace();
        }
    }
    @FXML
    void user_login(ActionEvent event) throws SQLException {
        String userEmail = user_email.getText();

        try {
            User user = us.getByEmail(user_email.getText());

            String inputtedCode = code.getText();

            if (inputtedCode.equals(user.getConfirmationCode())) {
                cAlert.generateAlert("WARNING", "Code valide");
                String email = user_email.getText();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatePass.fxml"));
                Parent root = loader.load();
                /*UpdatePass controller = loader.getController();
                controller.initData(userEmail);*/
                Scene scene = new Scene(root);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();


            } else {
                cAlert.generateAlert("WARNING", "Code non valide");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String email) {
        userEmail = email;
    }

  /*  @FXML
    private void loadCodeFXML(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Code.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }*/


    @FXML
    void retourPagePrecedente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


}

