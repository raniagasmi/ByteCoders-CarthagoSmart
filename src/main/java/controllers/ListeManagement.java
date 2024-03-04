package controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Reservation;
import services.ReservationService;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Properties;




public class ListeManagement {
    @FXML
    private TableColumn<Reservation, String> nom_event;
    @FXML
    private TableColumn<Reservation, String> email;
    @FXML
    private TableColumn<Reservation, Integer> numero;
    @FXML
    private TableColumn<Reservation, Float> nbr_place;
    @FXML
    private TableView<Reservation> tableView;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableColumn<Reservation, Void> confirmationColumn;
    private RenderedImage qrCodeImage;

    public ListeManagement() throws WriterException {
    }

    @FXML
    void initialize() {
        ReservationService reservationService = new ReservationService();
        ObservableList<Reservation> reservationData = FXCollections.observableArrayList(); // Initialise reservationData

        try {
            reservationData.addAll(reservationService.getAll1());
        } catch (SQLException e) {
            // Gérer les erreurs de récupération depuis la base de données
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
        // Assigner les cell value factories aux colonnes pour déterminer comment chaque propriété de Reservation sera affichée
        nom_event.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        nbr_place.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));

        // Configurer la colonne de confirmation pour afficher des boutons
        confirmationColumn.setCellFactory(param -> new TableCell<>() {
            private final Button confirmationButton = new Button("Confirmer");

            {
                confirmationButton.setOnAction(event -> {
                    // Récupérer la réservation correspondante à cette ligne
                    Reservation reservation = getTableView().getItems().get(getIndex());
                    // Envoyer un e-mail de confirmation à l'adresse e-mail de cette réservation
                    sendConfirmationEmail(reservation.getEmail());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(confirmationButton);
                }
            }
        });

        // Assigner la liste observable à la TableView
        tableView.setItems(reservationData);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

        });
    }
  


    private void sendConfirmationEmail(String recipientEmail) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Configurer le compte d'expédition
        String senderEmail = "cherif.benhassine@esprit.tn";
        String password = "Achraf2006+";

        // Créer une session de messagerie
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            // Créer un message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Confirmation de réservation");
            message.setText("Votre réservation a été confirmée avec succès.");

            // Envoyer le message
            Transport.send(message);
            System.out.println("E-mail de confirmation envoyé avec succès !");

            // Afficher une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("E-mail de confirmation envoyé avec succès !");
            alert.showAndWait();
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
            // Afficher une boîte de dialogue d'alerte en cas d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
            alert.showAndWait();
        }

    }




    public void trier(ActionEvent actionEvent) {
        TableColumn<Reservation, String> colonneNomEvent = nom_event;

        // Obtenez les données de la TableView
        ObservableList<Reservation> donnees = tableView.getItems();

        // Triez les données en fonction du nom de l'événement
        donnees.sort(Comparator.comparing(Reservation::getNom_event));
    }



    public void search(ActionEvent actionEvent) {

        String searchTerm = searchTextField.getText().trim().toLowerCase();

        // Assurez-vous que searchTerm est initialisé
        if (searchTerm.isEmpty()) {
            initialize(); // Appelez la méthode initialize() pour récupérer les données initiales
            return;
        }

        // Obtenez les données actuellement affichées dans la TableView
        ObservableList<Reservation> allReservations = tableView.getItems();

        // Créez une nouvelle liste pour stocker les résultats de la recherche
        ObservableList<Reservation> searchResults = FXCollections.observableArrayList();

        // Parcourez les données pour trouver les correspondances avec le terme de recherche
        for (Reservation reservation : allReservations) {
            // Vous pouvez ajouter des conditions supplémentaires ici pour rechercher sur plusieurs attributs de réservation
            if (reservation.getNom_event().toLowerCase().contains(searchTerm) ||
                    reservation.getEmail().toLowerCase().contains(searchTerm) ||
                    String.valueOf(reservation.getNumero()).toLowerCase().contains(searchTerm) ||
                    String.valueOf(reservation.getNbr_place()).toLowerCase().contains(searchTerm)) {
                searchResults.add(reservation);
            }
        }

        // Effacez les données précédentes dans la TableView
        tableView.getItems().clear();

        // Ajoutez les résultats de la recherche à la TableView
        tableView.setItems(searchResults);

        // Rafraîchissez la TableView pour afficher les nouveaux résultats de la recherche
        tableView.refresh();
    }


}







