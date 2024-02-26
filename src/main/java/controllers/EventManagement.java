package controllers;

import utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import models.Event;
import services.EventService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
//import path.to.EventManagement;


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
   // private Clipboard eventList;








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

                // Créer un nouvel objet Event avec les valeurs des champs de texte
                Event event = new Event(nom_event, categorie, date, heure, lieu, capaciteValue, coutValue);
                Es.ajouter(event);
                //eventData.add(event);
                tableView.getItems().add(event);

                clearInputFields();
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir des valeurs numériques valides pour la capacité et le coût");
            }

        }



        @FXML
        void initialize() {
           // Event event1 = new Event("Marathon", "sport", "2024-02-26", "10:00", "carthage", 500, 50.0f);
            //Event event2 = new Event("douz", "Culture", "2024-02-27", "9:00", "gbily", 4000, 75.0f);
            TableColumn<Event, Void> deleteColmn = new TableColumn<>("Supprimer");
            TableColumn<Event, Void> editColmn = new TableColumn<>("Modifier");

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

            });
            TableColumn<Event, Boolean> editColumn = new TableColumn<>("Modifier");
            editColumn.setSortable(false);
            editColumn.setCellValueFactory(new PropertyValueFactory<>(""));
            editColumn.setCellFactory(new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {
                @Override
                public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> p) {
                    return new EditButtonCell();
                }
            });
            // Ajouter la colonne de boutons à la TableView
            tableView.getColumns().add(deleteColumn);
            tableView.getColumns().add(editColumn);


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

}



