package ByteCoders.ViewController;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ByteCoders.Model.User;
import ByteCoders.Service.Encryption;
import ByteCoders.Service.UserServices;
import ByteCoders.Service.MyDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import ByteCoders.ViewController.cAlert;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class MDPController {

    public static String user;
    private static String userEmail;

    // public static String MDPController.userEmail =;


    @FXML
    private ImageView logo;

    @FXML
    private Label taperLabel;

    @FXML
    private TextField user_email;

    @FXML
    private Button submit;

    @FXML
    private TextField code;

    @FXML
    private Button submit1;

    @FXML
    private Label taperLabel1;

    @FXML
    private Button UpdatePassbtn;

    @FXML
    private Label nouvpass;

    @FXML
    private Label confnouvpass;

    @FXML
    private TextField fxoldpassword;

    @FXML
    private TextField fxnewpassword;


    cAlert cAlert = new cAlert();
    User u = null;


    @FXML
    void initialize() {
        assert user_email != null : "fx:id=\"user_email\" was not injected: check your FXML file 'MDP.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'MDP.fxml'.";
        assert code != null : "fx:id=\"code\" was not injected: check your FXML file 'MDP.fxml'.";
        assert submit1 != null : "fx:id=\"submit1\" was not injected: check your FXML file 'MDP.fxml'.";

    }


    UserServices us = new UserServices();

    @FXML
    private void user_restore(ActionEvent event) throws SQLException, Exception {

        taperLabel.setVisible(false);
        user_email.setVisible(false);;
        submit.setVisible(false);

        taperLabel1.setVisible(true);
        code.setVisible(true);
        submit1.setVisible(true);


        nouvpass .setVisible(false);
        UpdatePassbtn.setVisible(false);
        confnouvpass.setVisible(false);
        fxoldpassword.setVisible(false);
        fxnewpassword.setVisible(false);

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
                cAlert.generateAlert("WARNING", "Email n'existe pas");
            }
        } catch (SQLException e) {
            // Gérer les exceptions SQL
            e.printStackTrace();
        }
    }

    @FXML
    void user_login(ActionEvent event) throws SQLException {

        taperLabel.setVisible(false);
        user_email.setVisible(false);;
        submit.setVisible(false);

        taperLabel1.setVisible(false);
        code.setVisible(false);
        submit1.setVisible(false);


        nouvpass .setVisible(true);
        UpdatePassbtn.setVisible(true);
        confnouvpass.setVisible(true);
        fxoldpassword.setVisible(true);
        fxnewpassword.setVisible(true);

        String userEmail = user_email.getText();

        try {
            User user = us.getByEmail(user_email.getText());

            String inputtedCode = code.getText();

            if (inputtedCode.equals(user.getConfirmationCode())) {
                cAlert.generateAlert("WARNING", "Code valide");
                String email = user_email.getText();


            } else {
                cAlert.generateAlert("WARNING", "Code non valide");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }




    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    UserServices user_s = new UserServices();

    @FXML
    void UpdatePassword(ActionEvent actionEvent) throws Exception {

        if (!isValidPassword(fxoldpassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mot de passe invalide");
            alert.setContentText("le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial");
            alert.setHeaderText("CAlert Alert");
            alert.showAndWait();
            fxoldpassword.setText("");
            fxnewpassword.setText("");

            return;

        }
        if (!fxoldpassword.getText().equals(fxnewpassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("mots de passe ne correspondent pas");
            alert.setContentText("mot de passe et confirmer le mot de mot de passe devraient avoir le même contenu");
            alert.setHeaderText("CAlert Alert");
            alert.showAndWait();
            fxoldpassword.setText("");
            fxnewpassword.setText("");

            return;
        }

        System.out.println(Encryption.encrypt(fxoldpassword.getText()));
        user_s.updateUserByEmail(new User(u.getAddEmail() , Encryption.encrypt(fxoldpassword.getText())));
        System.out.println("password updated" );
        //  System.out.println("password is " + Encryption.encrypt(fxoldpassword.getText()));

        Parent root = FXMLLoader.load(getClass().getResource("/SignInUp.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

}