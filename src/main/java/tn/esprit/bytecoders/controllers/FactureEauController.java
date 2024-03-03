package tn.esprit.bytecoders.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import tn.esprit.bytecoders.Entity.Facture;
import tn.esprit.bytecoders.Services.FacturesService;
import tn.esprit.bytecoders.models.FactureModel;

public class FactureEauController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView account;

    @FXML
    private Button accueil;

    @FXML
    private Text adresse;

    @FXML
    private TableColumn<?, ?> libellé;
    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> date_ech;

    @FXML
    private Button déchets;

    @FXML
    private Button evenements;

    @FXML
    private Button facture;


    @FXML
    private ImageView logo;

    @FXML
    private Text num_contrat;

    @FXML
    private TableColumn<?, ?> num_fact;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<?, ?> statut;

    @FXML
    private Text username;

    // tableView items
    @FXML
    private TableView<FactureModel> tab;
    @FXML
    public TableColumn<FactureModel, Integer> id_facture;
    @FXML
    public TableColumn<FactureModel, String> libelle_facture;
    @FXML
    public TableColumn<FactureModel, String> date_facture;
    @FXML
    public TableColumn<FactureModel, String> date_ech_facture;
    @FXML
    public TableColumn<FactureModel, Double> montant_facture;
    @FXML
    public TableColumn<FactureModel, String> type_facture;
    public TableColumn<FactureModel, Boolean> payee_facture;

    FacturesService fs = new FacturesService();


    private ObservableList<FactureModel> facturesModels = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        id_facture.setCellValueFactory(new PropertyValueFactory<>("id_facture"));
        libelle_facture.setCellValueFactory(new PropertyValueFactory<>("libelle_facture"));
        date_facture.setCellValueFactory(new PropertyValueFactory<>("date_facture"));
        date_ech_facture.setCellValueFactory(new PropertyValueFactory<>("date_ech_facture"));
        montant_facture.setCellValueFactory(new PropertyValueFactory<>("montant_facture"));
        type_facture.setCellValueFactory(new PropertyValueFactory<>("type_facture"));
        payee_facture.setCellValueFactory(new PropertyValueFactory<>("payee_facture"));
        tab.setItems(facturesModels);
        addToTableView("");

        search.textProperty().addListener((observable, oldVal, newVal) -> {
            addToTableView(newVal);
        });
    }




    public void addToTableView(String newVal) {
        try {
            facturesModels.clear();
            List<Facture> factures = fs.getAll();
            factures.removeIf(facture -> !facture.getLibelle().contains(newVal));


            for (Facture facture : factures) {
                facturesModels.add(new FactureModel(
                        facture.getRef_facture(),
                        facture.getLibelle(),
                        facture.getDate().toString(),
                        facture.getDate_ech().toString(),
                        facture.getMontant(),
                        facture.getType().toString(),
                        facture.isEstPayee()
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
