package ByteCoders.ViewController;

import ByteCoders.Model.Roles;
import ByteCoders.Model.User;
import ByteCoders.Service.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignInUp {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private Text Seconnecterheader;

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
    private Text SeconnecterLabel1;

    @FXML
    private Text SeconnecterLabel2;

    @FXML
    private TextField CinTF;

    @FXML
    private Label cin;

    @FXML
    private Label nom;

    @FXML
    private TextField NomTF;

    @FXML
    private ToggleButton eyeToggle;

    @FXML
    private ImageView eyeIcon;

    @FXML
    private TextField UsernameTF;

    @FXML
    private TextField NumTlfnTF;

    @FXML
    private TextField addMailTF;

    @FXML
    private Label username;

    @FXML
    private Label num;

    @FXML
    private Label email;

    @FXML
    private Label mdp;

    @FXML
    private Label roleUser;

    @FXML
    private Button addButton;

    @FXML
    private Button chooseImageButton;

    @FXML
    private ComboBox<Roles> role;

    @FXML
    private Label prenom;

    @FXML
    private TextField PrenomTF;

    @FXML
    private PasswordField MdpPF;

    @FXML
    private TextField MdpTF;

    @FXML
    private ImageView urlImage;

    @FXML
    private TextField imageUrlTF;

    @FXML
    private Text Seconnecterbtn2;

    @FXML
    private AnchorPane RightPane;

    @FXML
    private Button BtnSinscrire;

    @FXML
    private Button toNous;

    @FXML
    private void AddUser(ActionEvent event) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IOException, InterruptedException {
        UserServices user_service = new UserServices();
        User u = new User();
        ValidateEmail e = new ValidateEmail();



// Validation des entrées utilisateur
        int x = 0;
        if (PrenomTF.getText().matches("[a-zA-Z]+")==false || PrenomTF.getText().length() == 0) {
            PrenomTF.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            x++;
        } else {
            PrenomTF.setStyle(null);
        }
        if (NomTF.getText().matches("[a-zA-Z]+")==false || NomTF.getText().length() == 0) {
            NomTF.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            x++;
        } else {
            NomTF.setStyle(null);
        }

        // String phoneNumber = NumTlfnTF.getText();
        String phoneNumber = NumTlfnTF.getText();
        if (!ValidPhoneNumber.validateNumber(phoneNumber)|| NumTlfnTF.getText().length()!=8) {
            NumTlfnTF.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            x++;
        } else {
            NumTlfnTF.setStyle(null);
        }

        String email = addMailTF.getText().trim(); // Remove spaces before and after the email

        if (email.isEmpty() || !e.isEmailValid(email)) {
            addMailTF.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            // Display an appropriate error message to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
            x++; // Increment x to indicate a validation error
        } else {
            addMailTF.setStyle(null); // Reset the style
        }

        // Vérification de l'URL de l'image
        String imageUrl = imageUrlTF.getText();
        if (imageUrl.isEmpty()) {
            // Si l'URL de l'image est vide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir une image.");
            alert.showAndWait();
            x++; // Incrémenter x pour indiquer une erreur de validation
        }

// Si les entrées sont valides, ajoutez l'utilisateur
        if (x == 0) {
            // Remplir les détails de l'utilisateur
            u.setCin(Integer.parseInt(CinTF.getText()));
            u.setNom(NomTF.getText());
            u.setPrenom(PrenomTF.getText());
            u.setUsername(UsernameTF.getText());
            u.setNumTlfn(Integer.parseInt(NumTlfnTF.getText()));
            u.setAddEmail(addMailTF.getText());
            u.setMdp(MdpTF.getText());
            u.setRoleUser(role.getSelectionModel().getSelectedItem());
            u.setUrlImage(imageUrl); // Ajouter l'URL de l'image

            try {
                String password = MdpTF.getText();
                u.setMdp(password);

                // Ajouter l'utilisateur via le service
                user_service.addUser(u);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Un nouveau utilisateur a été ajouté avec succès");
                alert.showAndWait();
            } catch (SQLException msg) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(msg.getMessage());
                alert.showAndWait();
                throw new RuntimeException(msg);
            }
        }

    }
    @FXML
    void SinscrireSlideBtn(ActionEvent event) {

        user_name.setVisible(false);
        SeconnecterLabel1.setVisible(false);
        user_password.setVisible(false);
        SeconnecterLabel2.setVisible(false);
        submit.setVisible(false);
        MDP.setVisible(false);
        Seconnecterbtn2.setVisible(false);
        toNous.setVisible(false);
        BtnSinscrire.setVisible(false);
        Seconnecterheader.setVisible(false);

        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), leftPane);
        transition1.setToX(leftPane.getTranslateX() + 300); // Move left by 50 pixels (adjust as needed)
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), RightPane);
        transition2.setToX(RightPane.getTranslateX() - 880 ); // Move left by 50 pixels (adjust as needed)
        transition1.play();
        transition2.play();


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            cin.setVisible(true);
            CinTF.setVisible(true);
            nom.setVisible(true);
            NomTF.setVisible(true);
            eyeToggle.setVisible(true) ;
            eyeIcon.setVisible(true) ;
            UsernameTF.setVisible(true);
            NumTlfnTF.setVisible(true);
            addMailTF.setVisible(true);
            num.setVisible(true);
            roleUser.setVisible(true);
            addButton.setVisible(true);
            chooseImageButton.setVisible(true);
            role.setVisible(true);
            prenom.setVisible(true);
            PrenomTF.setVisible(true);
            MdpPF.setVisible(true);
            MdpTF.setVisible(true);
            mdp.setVisible(true);
            urlImage.setVisible(true);
            imageUrlTF.setVisible(true);
            Seconnecterbtn2.setVisible(true);
            email.setVisible(true);

        }));
        timeline.play();

    }

    @FXML
    void addImage(ActionEvent event) throws MalformedURLException {
        chooseImage();

    }

    void chooseImage() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                // Obtenez l'URL de l'image choisie
                String imageUrl = selectedFile.toURI().toURL().toExternalForm();

                // Enregistrez l'URL de l'image dans la base de données
                // Vous devez implémenter cette fonctionnalité pour enregistrer l'URL dans la base de données
                //saveImageUrlToDatabase(imageUrl);

                imageUrlTF.setText(imageUrl);

                // Affichez l'image dans l'ImageView

                Image image = new Image(imageUrl);
                urlImage.setImage(image);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        // System.out.println("hjb");
    }



    @FXML
    void seConnecter(ActionEvent event) {

    }

    @FXML
    void mdp(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/MDP.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

    @FXML
    void toNous(ActionEvent event) {


    }

    @FXML
    private void togglePasswordVisibility(ActionEvent event) {
        if (eyeToggle.isSelected()) {
            // Afficher le mot de passe en clair
            MdpPF.setVisible(false);
            MdpTF.setVisible(true);
            MdpTF.setText(MdpPF.getText());

            // Changer l'icône de l'œil
            eyeIcon.setImage(new Image(getClass().getResourceAsStream("/eye.png")));
        } else {
            // Masquer le mot de passe en clair
            MdpTF.setVisible(false);
            MdpPF.setVisible(true);
            MdpPF.setText(MdpTF.getText());

            // Changer l'icône de l'œil
            eyeIcon.setImage(new Image(getClass().getResourceAsStream("/eye-off.png")));
        }
    }

    @FXML
    private void user_login(ActionEvent event) throws IOException, SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
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
                Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
                Scene scene = new Scene(root);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            //à ouvrir
           //  TwilioSmsSender.sendSms(u.getNumTlfn(), "Hello " + u.getNom() + ", you have successfully logged in.");
        } else {
            JOptionPane.showMessageDialog(null, "username ou Mot De Passe Introuvable");
        }
    }



}
