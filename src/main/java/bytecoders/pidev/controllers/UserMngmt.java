package bytecoders.pidev.controllers;

import bytecoders.pidev.models.Roles;
import bytecoders.pidev.models.User;
import bytecoders.pidev.services.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserMngmt implements Initializable {

    private Connection cnx;

    @FXML
    private ImageView urlImage;

    @FXML
    private TextField imageUrlTF;


    @FXML
    private Button chooseImageButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CinTF;

    @FXML
    private TextField ImageTF;

    @FXML
    private PasswordField MdpPF;

    @FXML
    private TextField MdpTF;

    @FXML
    private TextField NumTlfnTF;

    @FXML
    private TextField UsernameTF;

    @FXML
    private TextField addMailTF;

    @FXML
    private Label RoleTF;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField PrenomTF;

    @FXML
    private ComboBox<Roles> role;

    @FXML
    private Button afficherbutton;

    @FXML
    private Button addButton;

    @FXML
    private ImageView eyeIcon;

    @FXML
    private ToggleButton eyeToggle;

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
    void initialize() {
        assert eyeToggle != null : "fx:id=\"eyeToggle\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert CinTF != null : "fx:id=\"CinTF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert NumTlfnTF != null : "fx:id=\"NumTlfnTF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert addMailTF != null : "fx:id=\"addMailTF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert MdpPF != null : "fx:id=\"MdpPF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert RoleTF != null : "fx:id=\"RoleTF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert afficherbutton != null : "fx:id=\"afficherbutton\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert chooseImageButton != null : "fx:id=\"chooseImageButton\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert role != null : "fx:id=\"role\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert NomTF != null : "fx:id=\"NomTF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert PrenomTF != null : "fx:id=\"PrenomTF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert UsernameTF != null : "fx:id=\"UsernameTF\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert eyeIcon != null : "fx:id=\"eyeIcon\" was not injected: check your FXML file 'userMngmt.fxml'.";
        assert MdpTF != null : "fx:id=\"MdpTF\" was not injected: check your FXML file 'userMngmt.fxml'.";



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role.getItems().addAll(Roles.values());
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
    private void AddUser(ActionEvent event) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UserServices user_service = new UserServices();
        User u = new User();


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
        if (NumTlfnTF.getText().matches("[a-zA-Z]") || NumTlfnTF.getText().length()!=8) {
            NumTlfnTF.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            x++;
        } else {
            NumTlfnTF.setStyle(null);
        }

        String email = addMailTF.getText().trim(); // Supprimer les espaces avant et après l'e-mail

        if (email.isEmpty() || !email.matches(".+@.+\\..+")) {
            addMailTF.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
            // Afficher un message d'erreur approprié à l'utilisateur
            // Par exemple, avec une boîte de dialogue
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir une adresse e-mail valide.");
            alert.showAndWait();
            x++; // Incrémenter x pour indiquer une erreur de validation
        } else {
            addMailTF.setStyle(null); // Réinitialiser le style
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
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                throw new RuntimeException(e);
            }
        }

    }


    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @FXML
   void ShowUser(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/AfficherList.fxml")));
        //Parent root = null;

        try {
            NomTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @FXML
    void addImage(ActionEvent event) throws MalformedURLException {
        chooseImage();
    }


}
