package bytecoders.pidev.controllers;

import bytecoders.pidev.models.Roles;
import bytecoders.pidev.models.User;
import bytecoders.pidev.services.UserServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class EditUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane modifierB;

    @FXML
    private TextField CinTF;

    @FXML
    private TextField UsernameTF;

    @FXML
    private TextField NumTlfnTF;

    @FXML
    private TextField addMailTF;

    @FXML
    private Label RoleTF;


    @FXML
    private ImageView urlImage;

    @FXML
    private ComboBox<Roles> role;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField PrenomTF;

    @FXML
    private PasswordField MdpPF;

    @FXML
    private ImageView eyeIcon;

    @FXML
    private TextField MdpTF;


    @FXML
    private Button modifierBouton;


    @FXML
    private TextField confirmationCodeC;

    @FXML
    private Button retour;
    private UserServices userService;

    {
        userService = new UserServices();
    }


    // Déclaration de la variable selectedUser
    private User selectedUser;

    private User CurrentUser ;



    @FXML
    void initialize() {
        assert modifierB != null : "fx:id=\"modifierB\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert CinTF != null : "fx:id=\"CinTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert UsernameTF != null : "fx:id=\"UsernameTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert NumTlfnTF != null : "fx:id=\"NumTlfnTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert addMailTF != null : "fx:id=\"addMailTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert RoleTF != null : "fx:id=\"RoleTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert role != null : "fx:id=\"role\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert NomTF != null : "fx:id=\"NomTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert PrenomTF != null : "fx:id=\"PrenomTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert MdpPF != null : "fx:id=\"MdpPF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert eyeIcon != null : "fx:id=\"eyeIcon\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert MdpTF != null : "fx:id=\"MdpTF\" was not injected: check your FXML file 'EditUser.fxml'.";
        assert confirmationCodeC != null : "fx:id=\"confirmationCodeC\" was not injected: check your FXML file 'EditUser.fxml'.";


        role.getItems().addAll(Roles.values());

       /* usernamec.setCellValueFactory(new PropertyValueFactory<>("username"));
        numTlfnc.setCellValueFactory(new PropertyValueFactory<>("numTlfn"));
        addEmailc.setCellValueFactory(new PropertyValueFactory<>("addEmail"));
        mdpc.setCellValueFactory(new PropertyValueFactory<>("mdp"));
*/


    }



    public void initData(User selectedUser) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        this.selectedUser = selectedUser;

        // Afficher les données de l'utilisateur sélectionné dans les champs d'édition
        CinTF.setText(String.valueOf(selectedUser.getCin()));
        NomTF.setText(selectedUser.getNom());
        PrenomTF.setText(selectedUser.getPrenom());
        UsernameTF.setText(selectedUser.getUsername());
        NumTlfnTF.setText(String.valueOf(selectedUser.getNumTlfn()));
        addMailTF.setText(selectedUser.getAddEmail());
        MdpTF.setText(selectedUser.getMdp());
        role.setItems(FXCollections.observableArrayList(Roles.values()));
        role.setValue(selectedUser.getRoleUser());
        confirmationCodeC.setText(selectedUser.getConfirmationCode());
       // RoleTF.setText(selectedUser.getRoleUser().toString());

        // Assurez-vous que le ComboBox role est rempli avec les rôles appropriés
        // NomTF.setText(selectedUser.getNom());
        // PrenomTF.setText(selectedUser.getPrenom());
        // MdpTF.setText(selectedUser.getMdp());
    }

    @FXML
    void modifier(ActionEvent event) {
        modifierUser();
    }


        @FXML
        void modifierUser(){
        try {
            // Récupérer les données saisies dans les champs de texte
            //int id = Integer.parseInt(idMod.getText());

           // int cin = Integer.parseInt(CinTF.getText());
           // String newNom = NomTF.getText();
          //  String newPrenom = PrenomTF.getText();
            String newUsername = UsernameTF.getText();
            int newNum = Integer.parseInt(NumTlfnTF.getText());
            String newAdd = addMailTF.getText();
            String newMdp = MdpTF.getText();
            //Roles newrole = role.getValue();
            //String newCode = confirmationCodeC.getText();
            //String newurlImage = urlImage.getTexte();



            // Créer un objet User avec les nouvelles valeurs
            User user= new User(/*cin, newNom, newPrenom,*/ newUsername, newNum, newAdd, newMdp/*, newrole, newCode,newurlImage*/);
                //    User user = new User(newUsername, newNum, newAdd, newMdp,newurlImage);

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
        }

    private void afficherErreur(String titre, String contenu) {
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
    }

    @FXML
    void retourPagePrecedente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AfficherList.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


    @FXML
    void DesactiverProfile(ActionEvent event) {
        if (showConfirmationDialog("Etes-vous sûr de vouloir désactiver votre compte?")) {
            try {
                UserServices us = new UserServices();

                us.InvertStatus(u);

            } catch (SQLException e) {
                e.printStackTrace();

            }
            try {
                UserService us = new UserService();

                FXMLLoader loader = new FXMLLoader(MainFx.class.getResource("LoginRegistrationPage.fxml"));
                AnchorPane root = loader.load();

                LoginRegistrationPageController controller = loader.getController();
                profileRoot.getChildren().setAll(root);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);


        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
