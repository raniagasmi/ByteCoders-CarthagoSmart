package ByteCoders.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import ByteCoders.Model.Reservation;
import ByteCoders.Service.ReservationService;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ReservationManagement {

    @FXML
    private  TextField txtnom_event;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtnumero;

    @FXML
    private TextField txtnbr_place;

    @FXML
    private Button confirmer;

    // Méthode appelée lorsque le bouton confirmer est cliqué
    @FXML
    void confirmer (ActionEvent event) {
        // Récupérer les données des champs
        String nom_event = txtnom_event.getText();
        String email = txtemail.getText();
        int numero= Integer.parseInt(txtnumero.getText());
        int nbr_place = Integer.parseInt(txtnbr_place.getText());

        if (!isValidEmail(email)) {
            showAlert("Format d'email invalide", "Veuillez saisir un email valide.");
            return;
        }

        // Créer un objet Reservation avec les données récupérées
        Reservation reservation = new Reservation(nom_event, email,numero, nbr_place);

        // Ajouter la réservation à la base de données en utilisant le service de réservation
        ReservationService reservationService = new ReservationService();
        try {
            reservationService.ajouter1(reservation);
            System.out.println("Réservation confirmée avec succès !");
            clearFields();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la confirmation de la réservation : " + e.getMessage());
            // Gérer l'erreur, par exemple, afficher un message à l'utilisateur indiquant que la réservation a échoué.
        }
    }


    private boolean isValidEmail(String email) {
        // Expression régulière pour valider le format d'email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }


    private void showAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        txtnom_event.clear();
        txtemail.clear();
        txtnumero.clear();
        txtnbr_place.clear();
    }
    @FXML
    public void handleacceuil(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    @FXML
    public void handlefacture(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/menuPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    @FXML
    public void handledechet(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gestionDECHETS.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

}