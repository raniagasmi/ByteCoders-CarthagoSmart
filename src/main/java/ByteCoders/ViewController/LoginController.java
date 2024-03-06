/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ByteCoders.ViewController;

import ByteCoders.Model.Roles;
import ByteCoders.Model.User;
import ByteCoders.Service.Session;
import ByteCoders.Service.UserService;
import ByteCoders.Service.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;


public class LoginController {


/*
  private static User currentUtilisateur;
    private static String currentAdresse = "";
    public static User getCurrentUtilisateur() {
        return currentUtilisateur;
    }
    public static String getCurrentAdresse(){
        return currentAdresse;
    }

    public static void setCurrentAdresse(String adresse){
        currentAdresse = adresse;
    }*/


    @FXML
    private TextField user_name;
    @FXML
    private TextField user_password;
    @FXML
    private Button submit;
    @FXML
    private Button MDP;



    @FXML
    private Button inscrire;



    @FXML
    void initialize() {
        assert user_name != null : "fx:id=\"user_name\" was not injected: check your FXML file 'Login.fxml'.";
        assert user_password != null : "fx:id=\"user_password\" was not injected: check your FXML file 'Login.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'Login.fxml'.";
        assert MDP != null : "fx:id=\"MDP\" was not injected: check your FXML file 'Login.fxml'.";
        assert inscrire != null : "fx:id=\"inscrire\" was not injected: check your FXML file 'Login.fxml'.";

    }

    @FXML
    private void user_login(ActionEvent event) throws IOException, SQLException {
        String username = user_name.getText();
        String password = user_password.getText();
        UserServices user_service = new UserServices();
        User u = user_service.Login(username, password);


        if (u != null) { // Vérifie si l'utilisateur existe
            Session.setLoggedInUser(u);

            Notifications not = Notifications.create()
                    .title("Login Successful")
                    .text("Bienvenue " + Session.getLoggedInUser().getPrenom())
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT);
            not.darkStyle();
            not.show();
           // System.out.println(u.getRoleUser());

            if (u.getRoleUser().equals(Roles.ADMIN)) {
                Parent root = FXMLLoader.load(getClass().getResource("/AfficherList.fxml"));
                Scene scene = new Scene(root);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else /*if (!u.getRoleUser().equals(Roles.ADMIN))*/{
                System.out.println(u.getRoleUser());
                Parent root = FXMLLoader.load(getClass().getResource("/userMngmt.fxml"));
                Scene scene = new Scene(root);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
          //à ouvrir
            // TwilioSmsSender.sendSms(u.getNumTlfn(), "Hello " + u.getNom() + ", you have successfully logged in.");
        } else {
            JOptionPane.showMessageDialog(null, "username ou Mot De Passe Introuvable");
        }
    }


    @FXML
    private void mdp(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("/MDP.fxml"));
         Scene scene = new Scene(root);
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(scene);
         window.show();
    }

    @FXML
    void inscrire(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/userMngmt.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


}
