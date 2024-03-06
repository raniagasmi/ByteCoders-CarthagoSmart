package ByteCoders.ViewController;

import ByteCoders.Service.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import ByteCoders.Model.Event;
import ByteCoders.Service.EventService;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
//import path.to.EventManagement;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class EventManagement {

    public TableColumn IDColmn;
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ajouter;

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    @FXML
    private TextField txtcapacite;

    @FXML
    private TextField txtcategorie;

    @FXML
    private TextField txtcout;

    @FXML
    private TextField txtdate;

    @FXML
    private TextField txtlieu;

    @FXML
    private TextField txtheure;
    @FXML
    private TextField txtnom_event;

    @FXML
    private TableColumn<Event, String> nom_eventColmn;

    @FXML
    private TableColumn<Event, String> categorieColmn;
    @FXML
    private TableColumn<Event, String> dateColmn;
    @FXML
    private TableColumn<Event, String> heureColmn;
    @FXML
    private TableColumn<Event, String> lieuColmn;
    @FXML
    private TableColumn<Event, Integer> capaciteColmn;
    @FXML
    private TableColumn<Event, Float> coutColmn;
    @FXML
   // private TableColumn<Event, void> deleteColmn;
    private ObservableList<Event> eventData = FXCollections.observableArrayList();
    @FXML
    private TableView<Event> tableView;





    private void clearInputFields() {
        txtnom_event.clear();
        txtcapacite.clear();
        txtcategorie.clear();
        txtcout.clear();
        txtheure.clear();
        txtdate.clear();
        txtlieu.clear();
    }



        public void ajouter(ActionEvent actionEvent) throws SQLException {
            System.out.println("method ajout");
            String nom_event = txtnom_event.getText();
            String capacite = txtcapacite.getText();
            String categorie = txtcategorie.getText();
            String cout = txtcout.getText();
            String date = txtdate.getText();
            String lieu = txtlieu.getText();
            String heure = txtheure.getText();

            // Valider les entrées
            if (nom_event.isEmpty() || capacite.isEmpty() || categorie.isEmpty() || cout.isEmpty() || date.isEmpty() || lieu.isEmpty() || heure.isEmpty()) {
                System.out.println("Veuillez remplir tous les champs");
                return;
            }

            // Valider les valeurs numériques
            try {
                EventService Es = new EventService();
                int capaciteValue = Integer.parseInt(capacite);
                float coutValue = Float.parseFloat(cout);

                // Valider le format de la date (YYYY-MM-DD) et de l'heure (HH:MM)
                if (!date.matches("\\d{4}-\\d{2}-\\d{2}") || !heure.matches("\\d{2}:\\d{2}")) {
                    showAlert(Alert.AlertType.ERROR, "Format invalide", "Le format de la date ou de l'heure est invalide.\nUtilisez le format YYYY-MM-DD pour la date et HH:MM pour l'heure.");
                    return;
                }

                // Créer un nouvel objet Event avec les valeurs des champs de texte
                Event event = new Event(nom_event, categorie, date, heure, lieu, capaciteValue, coutValue);
                Es.ajouter(event);
                eventData.add(event);
                clearInputFields();
                showAlert(Alert.AlertType.INFORMATION, "Succès", "L'événement a été ajouté avec succès.");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Valeurs numériques invalides", "Veuillez saisir des valeurs numériques valides pour la capacité et le coût.");
            }
        }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




        @FXML
        void initialize() {

           // Event event1 = new Event("Marathon", "sport", "2024-02-26", "10:00", "carthage", 500, 50.0f);
            //Event event2 = new Event("douz", "Culture", "2024-02-27", "9:00", "gbily", 4000, 75.0f);
            TableColumn<Event, Void> deleteColmn = new TableColumn<>("Supprimer");
            TableColumn<Event, Void> qrCodeColumn = new TableColumn<>("Générer QR Code");



            EventService eventService = new EventService();
            try {
                eventData.addAll(eventService.getAllEvents());
            } catch (SQLException e) {
                // Gérer les erreurs de récupération depuis la base de données
                System.out.println("Erreur lors de la récupération des événements : " + e.getMessage());
            }

            // Assigner les cell value factories aux colonnes pour déterminer comment chaque propriété d'Event sera affichée
            nom_eventColmn.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
            categorieColmn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            dateColmn.setCellValueFactory(new PropertyValueFactory<>("date"));
            heureColmn.setCellValueFactory(new PropertyValueFactory<>("heure"));
            lieuColmn.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            capaciteColmn.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            coutColmn.setCellValueFactory(new PropertyValueFactory<>("cout"));

            // Assigner la liste observable à la TableView
            tableView.setItems(eventData);
            TableColumn<Event, Boolean> deleteColumn = new TableColumn<>("Supprimer");

            deleteColumn.setSortable(false);

            deleteColumn.setCellValueFactory(new PropertyValueFactory<>(""));

            deleteColumn.setCellFactory(new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {
                @Override
                public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> p) {
                    return new DeleteButtonCell();
                }

            }
            );

            // Ajouter la colonne de boutons à la TableView
            tableView.getColumns().add(deleteColumn);
            //tableView.getColumns().add(editColumn);

            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    // Mettre à jour les champs de texte avec les détails de l'événement sélectionné
                    txtnom_event.setText(newSelection.getNom_event());
                    txtcategorie.setText(newSelection.getCategorie());
                    txtdate.setText(newSelection.getDate());
                    txtheure.setText(newSelection.getHeure());
                    txtlieu.setText(newSelection.getLieu());
                    txtcapacite.setText(String.valueOf(newSelection.getCapacite()));
                    txtcout.setText(String.valueOf(newSelection.getCout()));
                }
            });


        }




    public class DeleteButtonCell extends TableCell<Event, Boolean> {
         Button deleteButton = new Button("Supprimer");
         EventService EventService = new EventService();

        public DeleteButtonCell() {
            deleteButton.setOnAction((ActionEvent event) -> {
                Event eventToDelete = (Event) DeleteButtonCell.this.getTableView().getItems().get(DeleteButtonCell.this.getIndex());
                try {
                    EventService.supprimer(eventToDelete);

                    DeleteButtonCell.this.getTableView().getItems().remove(eventToDelete);
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la suppression de l'événement : " + e.getMessage());
                }
            });
        }


        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(deleteButton);

            } else {
                setGraphic(null);
            }
        }

    }

    public class EditButtonCell extends TableCell<Event, Boolean> {
         Button editButton = new Button("Modifier");
         EventService eventService = new EventService();



        // Mettre à jour l'affichage du bouton en fonction de la présence ou de l'absence de données dans la cellule
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            // Vérifier si la cellule est vide ou non
            if (!empty) {
                // Si la cellule n'est pas vide, afficher le bouton "Modifier"
                setGraphic(editButton);
            } else {
                // Si la cellule est vide, ne rien afficher
                setGraphic(null);
            }
        }
        }

    public class ButtonCell<S> extends TableCell<S, Boolean> {
        private final Button button;
        private final EventService eventService = new EventService();

        public ButtonCell(String buttonText) {
            this.button = new Button(buttonText);
            this.button.setOnAction(event -> {
                S item = getTableView().getItems().get(getIndex());
                if (buttonText.equals("Supprimer")) {
                    getTableView().getItems().remove(item);
                    // Supprimer l'objet de la base de données
                    if (item instanceof Event) {
                        Event eventToDelete = (Event) item;
                        try {
                            eventService.supprimer(eventToDelete);
                        } catch (SQLException e) {
                            // Gérer les erreurs de suppression de la base de données
                            System.out.println("Erreur lors de la suppression de l'événement : " + e.getMessage());
                        }
                    }
                } else if (buttonText.equals("Modifier")) {
                    if (item instanceof Event) { Event eventToDelete = (Event) item;
                        try {
                            eventService.supprimer(eventToDelete);
                        } catch (SQLException e) {
                            // Gérer les erreurs de suppression de la base de données
                            System.out.println("Erreur lors de la modification de l'événement : " + e.getMessage());
                        }
                        }


                }
            });

        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }

}

    public void modifier(ActionEvent actionEvent) {
        // Vérifier si une ligne est sélectionnée
        if (tableView.getSelectionModel().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner un événement à modifier.");
            return;
        }

        // Récupérer l'index de la ligne sélectionnée
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        // Récupérer l'événement sélectionné
        Event selectedEvent = tableView.getSelectionModel().getSelectedItem();

        // Mise à jour des détails de l'événement avec les nouvelles valeurs des champs de texte
        selectedEvent.setNom_event(txtnom_event.getText());
        selectedEvent.setCategorie(txtcategorie.getText());
        selectedEvent.setDate(txtdate.getText());
        selectedEvent.setHeure(txtheure.getText());
        selectedEvent.setLieu(txtlieu.getText());

        // Convertir les valeurs en entier et en flottant
        try {
            selectedEvent.setCapacite(Integer.parseInt(txtcapacite.getText()));
            selectedEvent.setCout(Float.parseFloat(txtcout.getText()));
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de modification", "Veuillez saisir des valeurs numériques valides pour la capacité et le coût.");
            return;
        }

        // Mettre à jour l'événement dans la base de données
        EventService eventService = new EventService();
        try {
            eventService.modifier(selectedEvent);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "L'événement a été modifié avec succès.");

            // Mettre à jour la ligne dans la TableView
            tableView.getItems().set(selectedIndex, selectedEvent);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la modification de l'événement : " + e.getMessage());
        }

    }
    public void pdf(ActionEvent actionEvent) {   try {
        // Créer un document PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Créer un objet PDPageContentStream pour écrire le contenu sur la page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Définir la police
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        // Définir les coordonnées de départ pour écrire le contenu
        float y = page.getMediaBox().getHeight() - 50;

        // Ecrire les en-têtes de colonne
        contentStream.beginText();
        contentStream.newLineAtOffset(50, y);
        contentStream.showText("Nom de l'événement");
        contentStream.newLineAtOffset(150, 0);
        contentStream.showText("Catégorie");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Date");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Heure");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Lieu");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Capacité");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Coût");
        contentStream.endText();

        // Ecrire les données de la table
        y -= 20;
        for (Event event : tableView.getItems()) {
            contentStream.beginText();
            contentStream.newLineAtOffset(50, y);
            contentStream.showText(event.getNom_event());
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText(event.getCategorie());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(event.getDate());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(event.getHeure());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(event.getLieu());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(String.valueOf(event.getCapacite()));
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(String.valueOf(event.getCout()));
            contentStream.endText();
            y -= 20;
        }

        // Fermer le contentStream
        contentStream.close();

        // Enregistrer le document PDF
        File file = new File("C:\\Users\\LENOVO\\Desktop\\pidev\\Gestion_Evenements\\event.pdf");
        document.save(file);

        // Fermer le document
        document.close();

        showAlert(Alert.AlertType.INFORMATION, "Succès", "Le fichier PDF a été généré avec succès : " + file.getAbsolutePath());
    } catch (IOException e) {
        showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la génération du fichier PDF : " + e.getMessage());
    }
    }
    @FXML
    public void handleAcceuil(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    @FXML
    public void reservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ListeManagement.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


}




