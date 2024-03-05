package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

<<<<<<< HEAD


=======
>>>>>>> 778c81c01ff00e182c43909c38ef7e747f667aea
        // Charger la première interface
        FXMLLoader eventLoader = new FXMLLoader(MainFx.class.getResource("EventManagement.fxml"));
        Scene eventScene = new Scene(eventLoader.load(), 800, 600);
        primaryStage.setTitle("Gestion des événements");
        primaryStage.setScene(eventScene);
        primaryStage.show();

        // Créer et afficher le deuxième stage pour la deuxième interface
        Stage reservationStage = new Stage();
        FXMLLoader reservationLoader = new FXMLLoader(MainFx.class.getResource("ReservationManagement.fxml"));
        Scene reservationScene = new Scene(reservationLoader.load(), 600, 400);
        reservationStage.setTitle("réserver un évenement");
        reservationStage.setScene(reservationScene);
        reservationStage.show();

        // Créer et afficher le troisième stage pour la troisième interface
        Stage listeStage = new Stage();
        FXMLLoader listeLoader = new FXMLLoader(MainFx.class.getResource("ListeManagement.fxml"));
        Scene listeScene = new Scene(listeLoader.load(), 600, 400);
<<<<<<< HEAD
        listeStage.setTitle("gestion des événements");
=======
        listeStage.setTitle("Liste de gestion");
>>>>>>> 778c81c01ff00e182c43909c38ef7e747f667aea
        listeStage.setScene(listeScene);
        listeStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
