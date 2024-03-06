package ByteCoders.ViewController;

import ByteCoders.Model.Roles;
import ByteCoders.Model.User;
import ByteCoders.Service.UserService;
import ByteCoders.Service.UserServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.sql.SQLException;


public class EditUserController {

    @FXML
    private Button chooseImageButton;


    @FXML
    private AnchorPane rania;

    @FXML
    private ImageView urlImage;

    @FXML
    private PasswordField inputCPassword;

    @FXML
    private TextField imageUrlTF;

    @FXML
    private TextField UsernameTF;

    @FXML
    private TextField NumTlfnTF;

    @FXML
    private TextField addMailTF;

    @FXML
    private PasswordField MdpPF;

    @FXML
    private TextField MdpTF;

    @FXML
    private Button modifierBouton;

    @FXML
    private Button btnDesactiver;

    @FXML
    private Button retour;

    @FXML
    private ToggleButton eyeToggle;

    @FXML
    private ImageView eyeIcon;

    private UserServices userService;

    {
        userService = new UserServices();
    }


    // Déclaration de la variable selectedUser
    private User selectedUser;

    @FXML
    void initialize() {

        // role.getItems().addAll(Roles.values());

       /* usernamec.setCellValueFactory(new PropertyValueFactory<>("username"));
        numTlfnc.setCellValueFactory(new PropertyValueFactory<>("numTlfn"));
        addEmailc.setCellValueFactory(new PropertyValueFactory<>("addEmail"));
        mdpc.setCellValueFactory(new PropertyValueFactory<>("mdp"));
*/


    }



    public void initData(User selectedUser) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        this.selectedUser = selectedUser;


        // Afficher les données de l'utilisateur sélectionné dans les champs d'édition
        // CinTF.setText(String.valueOf(selectedUser.getCin()));
        //  NomTF.setText(selectedUser.getNom());
        //  PrenomTF.setText(selectedUser.getPrenom());
        UsernameTF.setText(selectedUser.getUsername());
        NumTlfnTF.setText(String.valueOf(selectedUser.getNumTlfn()));
        addMailTF.setText(selectedUser.getAddEmail());
        MdpTF.setText(selectedUser.getMdp());
        String imageUrl = selectedUser.getUrlImage();

        // Créer un objet Image à partir de l'URL
        Image image = new Image(imageUrl);

        // Créer un objet ImageView pour afficher l'image
        ImageView imageView = new ImageView(image);

        // Créer une mise en page pour contenir l'image
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        // Créer la scène
        Scene scene = new Scene(root, 400, 400);

        // Configurer la fenêtre principale
        Stage primaryStage = null;
        primaryStage.setTitle("Affichage d'image depuis une URL");
        primaryStage.setScene(scene);

        // Afficher la fenêtre
        primaryStage.show();
        // urlImage.setImage(selectedUser.getUrlImage());
        ////  role.setItems(FXCollections.observableArrayList(Roles.values()));
        // role.setValue(selectedUser.getRoleUser());
        //  confirmationCodeC.setText(selectedUser.getConfirmationCode());
        // RoleTF.setText(selectedUser.getRoleUser().toString());

        // Assurez-vous que le ComboBox role est rempli avec les rôles appropriés
        // NomTF.setText(selectedUser.getNom());
        // PrenomTF.setText(selectedUser.getPrenom());
        // MdpTF.setText(selectedUser.getMdp());
    }

    @FXML
    void addImage(ActionEvent event) throws MalformedURLException {
        chooseImage();

    }
    void chooseImage() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", ".png", ".jpg", "*.gif")
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
    void modifier(ActionEvent event)throws SQLException {
        modifierUser();
    }


    @FXML
    void modifierUser() {
        try {
            if (!MdpPF.getText().equals(inputCPassword.getText())) {
                afficherMessageErreur("Mots de passe ne correspondent pas", "Le mot de passe et la confirmation du mot de passe devraient avoir le même contenu");
                return;
            }
            if (MdpPF.getText().isEmpty()) {
                afficherMessageErreur("Mot de passe invalide", "Le mot de passe ne peut pas être vide");
                return;
            }

            // Mettre à jour les données de l'utilisateur
            updateUser();

            // Afficher un message de confirmation
            afficherMessage("Succès", "L'utilisateur a été modifié avec succès.");
        } catch (NumberFormatException e) {
            afficherMessageErreur("Erreur", "Veuillez saisir un ID valide.");
        } catch (SQLException e) {
            afficherMessageErreur("Erreur", "Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    void updateUser() throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String newUsername = UsernameTF.getText();
        int newNum = Integer.parseInt(NumTlfnTF.getText());
        String newAdd = addMailTF.getText();
        String newMdp = MdpTF.getText();

        User user = new User(newUsername, newNum, newAdd, newMdp, selectedUser.getUrlImage());
        // Utilisez la méthode update de votre service utilisateur pour mettre à jour les données de l'utilisateur
        userService.updateUser(user);
    }

 /*   @FXML
    void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", ".png", ".jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                // Obtenez l'URL de l'image choisie
                String imageUrl = selectedFile.toURI().toURL().toExternalForm();

                // Enregistrez l'URL de l'image dans la base de données
                int userId = u.getId();
                userService.updateImageUrl(userId, imageUrl);

                imageUrlTF.setText(imageUrl);

                // Affichez l'image dans l'ImageView
                Image image = new Image(imageUrl);
                urlImage.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }*/

    void afficherMessageErreur(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText("CAlert Alert");
        alert.showAndWait();
    }

    void afficherMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText("CAlert Alert");
        alert.showAndWait();
    }

       /* @FXML
        void modifierUser(){
        try {
            if( !MdpPF.getText().equals(inputCPassword.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("mots de passe ne correspondent pas");
                alert.setContentText("mot de passe et confirmer le mot de mot de passe devraient avoir le même contenu");
                alert.setHeaderText("CAlert Alert");
                alert.showAndWait();
                return ;
            }
            if( MdpPF.getText().isEmpty()  ){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("mots de passe invalid");
                alert.setContentText("le mot de passe ne peut pas être vide");
                alert.setHeaderText("CAlert Alert");
                alert.showAndWait();
                return ;
            }
            String newUsername = UsernameTF.getText();
            int newNum = Integer.parseInt(NumTlfnTF.getText());
            String newAdd = addMailTF.getText();
            String newMdp = MdpTF.getText();
            //Image newImg = setUrlImage(selectedUser.getUrlImage());  // Vous avez déjà récupéré l'URL

            User user= new User(newUsername, newNum, newAdd, newMdp,newImg);


            // Mettre à jour l'utilisateur dans la base de données
            userService.updateUser(user);

            // Afficher un message de confirmation
            afficherMessage("Succès", "L'utilisateur a été modifié avec succès.");
        } catch (NumberFormatException e) {
            afficherErreur("Erreur", "Veuillez saisir un ID valide.");
        } catch (SQLException e) {
            afficherErreur("Erreur", "Erreur lors de la modification de l'utilisateur : " + e.getMessage());
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
        }*/

   /* private void afficherErreur(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void afficherMessage(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }*/

    @FXML
    void retourPagePrecedente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AfficherList.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


    public boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);


        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    @FXML
    void DesactiverProfile(ActionEvent event) {
        if (showConfirmationDialog("Etes-vous sûr de vouloir désactiver votre compte?")) {
            try {
                UserServices us = new UserServices();
                User u = new User();

                us.InvertStatus(u.getAddEmail());

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
            try {
                UserServices us = new UserServices();
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/userMngmt.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

                SignInUp controller = loader.getController();
                // Assuming that 'rania' is a container in your 'SignInUp' controller
                controller.MainPane.getChildren().setAll(root);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


}