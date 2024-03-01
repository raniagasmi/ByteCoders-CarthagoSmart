package tn.esprit.bytecoders.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.bytecoders.Entity.Consommation;
import tn.esprit.bytecoders.Entity.Facture;
import tn.esprit.bytecoders.Entity.TypeFacture;
import tn.esprit.bytecoders.Services.FacturesService;
import tn.esprit.bytecoders.models.FactureModel;

public class FactureEauController implements Initializable {

    @FXML
    public CheckBox payee;
    FacturesService fs = new FacturesService();

    @FXML
    private DatePicker date;

    @FXML
    private DatePicker date_ech;

    @FXML
    private TextField facture;

    @FXML
    private TextField libelle;


    @FXML
    private TextField montant;


    @FXML
    private TableView<FactureModel> tab;

    @FXML
    private ComboBox<?> wasteTypeComboBox;


    //buttons
    @FXML
    private Button modifier;

    @FXML
    private Button reset;

    @FXML
    private Button supprimer;

    @FXML
    private Button ajouter;

    // tableView items
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


    private ObservableList<FactureModel> facturesModels = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        facture.setDisable(true);
        id_facture.setCellValueFactory(new PropertyValueFactory<>("id_facture"));
        libelle_facture.setCellValueFactory(new PropertyValueFactory<>("libelle_facture"));
        date_facture.setCellValueFactory(new PropertyValueFactory<>("date_facture"));
        date_ech_facture.setCellValueFactory(new PropertyValueFactory<>("date_ech_facture"));
        montant_facture.setCellValueFactory(new PropertyValueFactory<>("montant_facture"));
        type_facture.setCellValueFactory(new PropertyValueFactory<>("type_facture"));
        payee_facture.setCellValueFactory(new PropertyValueFactory<>("payee_facture"));
        tab.setItems(facturesModels);
        //addToTableView();
    }
    //create a tri method for the table view to sort the data by date or by montant or by type  or by payee or by libelle   or by date_ech or by id_facture


/**
    @FXML
    void addFacture(ActionEvent event) {
        if (!isFormValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez verifier vos données! ");
            alert.show();
            return;
        }
        try {
            if (facture.getText().isEmpty()) {
                Facture facture = new Facture(
                        libelle.getText(),
                        Date.valueOf(date.getValue()),
                        Date.valueOf(date_ech.getValue()),
                        Double.parseDouble(montant.getText()),
                        wasteTypeComboBox.getValue().toString().equals("ENERGIE") ? TypeFacture.ENERGY : TypeFacture.EAU,
                        payee.isSelected(),
                        1
                );
                fs.create(facture);
                addToTableView();
            }else{
                Facture facture = new Facture(
                        Integer.parseInt(this.facture.getText()),
                        libelle.getText(),
                        Date.valueOf(date.getValue()),
                        Date.valueOf(date_ech.getValue()),
                        Double.parseDouble(montant.getText()),
                        wasteTypeComboBox.getValue().toString().equals("ENERGIE") ? TypeFacture.ENERGY : TypeFacture.EAU,
                        payee.isSelected(),
                        1
                );
                fs.update(facture);
                addToTableView();
            }
            resetFacture();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez verifier vos données! ");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Facture ajoutée avec succès!");
        alert.show();
    }

    private boolean isFormValid() {
        return !libelle.getText().isEmpty() &&
                !date.getEditor().getText().isEmpty() &&
                !date_ech.getEditor().getText().isEmpty() &&
                !montant.getText().isEmpty() &&
                !payee.getText().isEmpty();
    }
    **/
/**
    @FXML
    void deleteFacture(ActionEvent event) {

        FactureModel factureModel = tab.getSelectionModel().getSelectedItem();
        Facture facture = fs.getFactureById(factureModel.getId_facture());
        fs.delete(facture);
        addToTableView();

        System.out.println("Facture supprimée avec succès!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Facture supprimée avec succès!");
        alert.show();
    }
**/
/**
    @FXML
    void resetFacture() {
        facture.clear();
        libelle.clear();
        date.getEditor().clear();
        date_ech.getEditor().clear();
        montant.clear();
        payee.setSelected(false);
    }
**/
    /**
    public void addToTableView() {
        try {
            facturesModels.clear();
            List<Facture> factures = fs.getAll();
            System.out.println(factures);
            for (Facture : factures) {
                facturesModels.add(new FactureModel(
                        facture.getId_facture(),
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
**/
    public void fillForm(Facture facture){
        this.facture.setText(String.valueOf(facture.getId_facture()));
        this.libelle.setText(facture.getLibelle());
        this.date.setValue(facture.getDate().toLocalDate());
        this.date_ech.setValue(facture.getDate_ech().toLocalDate());
        this.montant.setText(String.valueOf(facture.getMontant()));
        this.payee.setSelected(facture.isEstPayee());
    }

    /**
     @FXML


    void updateFacture(ActionEvent event) {
        FactureModel factureModel = tab.getSelectionModel().getSelectedItem();
        Facture facture = fs.getFactureById(factureModel.getId_facture());
        fillForm(facture);
    }
     **/


}




