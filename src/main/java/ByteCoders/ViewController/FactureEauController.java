package ByteCoders.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ByteCoders.Model.Facture;
import ByteCoders.Service.FacturesService;
import ByteCoders.Model.FactureModel;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FactureEauController {

    public CheckBox payee;
    public CheckBox non_payee;
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

        payee.setSelected(true);
        non_payee.setSelected(true);

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

            List<Facture> filteredFactures = new ArrayList<>();

            for (Facture facture : factures) {
                if ((facture.getType().toString().contains(newVal)) || (facture.getLibelle().contains(newVal))) {
                    if (payee.isSelected() && facture.isEstPayee())
                        filteredFactures.add(facture);

                    if (non_payee.isSelected() && !facture.isEstPayee())
                        filteredFactures.add(facture);
                }


            }


            for (Facture facture : filteredFactures) {
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


    public void onCheckboxCheck(ActionEvent actionEvent) {
        addToTableView(search.getText());
    }

    public void handlePayer(ActionEvent actionEvent) throws IOException {
        FactureModel factureModel = tab.getSelectionModel().getSelectedItem();


        Parent root = FXMLLoader.load(getClass().getResource("/paiementCopie.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        if (factureModel != null) {







/*
    FXMLLoader loader = new FXMLLoader(getClass().getResource("paiementCopie.fxml"));
            Parent targetRoot = loader.load();

            PaiementController paiementController = loader.getController();


            paiementController.setFactureId(factureModel.getId_facture() + "");

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(targetRoot));
            stage.show();
* */






        }


    }
}
