package tn.esprit.bytecoders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

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
        private TableColumn<?, ?> libellé;

        @FXML
        private ImageView logo;

        @FXML
        private Text num_contrat;

        @FXML
        private TableColumn<?, ?> num_fact;

        @FXML
        private TableColumn<?, ?> statut;

        @FXML
        private Text username;

        @FXML
        void initialize() {
            assert account != null : "fx:id=\"account\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert accueil != null : "fx:id=\"accueil\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert date_ech != null : "fx:id=\"date_ech\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert déchets != null : "fx:id=\"déchets\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert evenements != null : "fx:id=\"evenements\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert facture != null : "fx:id=\"facture\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert libellé != null : "fx:id=\"libellé\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert num_contrat != null : "fx:id=\"num_contrat\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert num_fact != null : "fx:id=\"num_fact\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert statut != null : "fx:id=\"statut\" was not injected: check your FXML file 'factureEAU.fxml'.";
            assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'factureEAU.fxml'.";

        }


    public void add(ActionEvent actionEvent) {

    }
}
