package bytecoders.pidev.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.ResourceBundle;

import bytecoders.pidev.models.User;
import bytecoders.pidev.services.Encryption;
import bytecoders.pidev.services.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;


public class UpdatePass {

    @FXML
    private ResourceBundle resources;

    private User u = null;

    @FXML
    private URL location;

    @FXML
    private PasswordField fxoldpassword;

    @FXML
    private PasswordField fxnewpassword;

    @FXML
    private Button UpdatePassbtn;

   // private static UpdatePass instance; // Champ statique pour stocker l'instance unique

   // private UpdatePass() {} // Constructeur privé pour empêcher l'instanciation directe

    // Méthode statique pour récupérer l'instance unique de UpdatePass
   /* public static UpdatePass getInstance() {
        if (instance == null) {
            instance = new UpdatePass();
        }
        return instance;
    }*/

    @FXML
    void UpdatePassword(ActionEvent event) throws Exception {

            String oldPassword = fxoldpassword.getText();
            String newPassword = fxnewpassword.getText();

            User u = UserServices.getCurrentUtilisateur();
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
    }

/*
    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    // Méthode pour initialiser l'utilisateur
    public void setUser(User user) {
        this.u = user;
    }

    // Méthode pour récupérer l'utilisateur
    public User getUser() {
        return u;
    }*/



