package tn.esprit.bytecoders.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

    @FXML
    void initialize() {


    }

}
