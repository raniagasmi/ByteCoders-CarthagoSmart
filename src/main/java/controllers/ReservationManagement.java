package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import models.Reservation;
import services.ReservationService;

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

}