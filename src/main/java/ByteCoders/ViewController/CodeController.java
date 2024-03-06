package ByteCoders.ViewController;

import ByteCoders.Model.User;
import ByteCoders.Model.User;
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
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class CodeController {

    private MDPController mdpController;
    public void setMdpController(MDPController mdpController){
        this.mdpController=mdpController;
    }

    private String userEmail;
    public String setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return userEmail;
    }
    User u = null ;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField code;
    @FXML
    private Button submit;
    /*@FXML
    private TextField pwd;*/

    cAlert cAlert = new cAlert();


    @FXML
    void initialize() {
        assert code != null : "fx:id=\"code\" was not injected: check your FXML file 'Code.fxml'.";
       // assert pwd != null : "fx:id=\"pwd\" was not injected: check your FXML file 'Code.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'Code.fxml'.";


        UnaryOperator<TextFormatter.Change> filterfield = change -> {
            String newText = change.getControlNewText();// and to only  and letters upper and lower
            if ( newText.length() <= 1) {
                return change;
            }
            return null;
        };
        code.setTextFormatter(new TextFormatter<>(filterfield));


        System.out.println(u.getConfirmationCode() + "is code");




    }


    UserServices us = new UserServices();

    @FXML
    void user_login(ActionEvent event) throws SQLException, IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Code.fxml"));
        Parent root = loader.load();
        CodeController codeController = loader.getController();
        codeController.setUserEmail(userEmail);

        u =us.getByEmail(codeController.setUserEmail(userEmail));
        String inputtedCode = code.getText();

        if (!inputtedCode.equals(u.getConfirmationCode())) {
            System.out.println(inputtedCode);
            System.out.println(u.getConfirmationCode());
            cAlert.generateAlert("WARNING", "Code Invalid");
            return;
        }


    }


    private void redirectToLoginScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void showSuccessNotification() {
        Notifications not = Notifications.create()
                .title("Changement")
                .text("Modification Acceptée")
                .hideAfter(Duration.seconds(5))
                .position(Pos.CENTER_RIGHT);
        not.darkStyle();
        not.show();
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


}
