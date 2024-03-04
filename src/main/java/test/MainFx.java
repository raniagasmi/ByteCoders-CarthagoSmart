package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

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
        reservationStage.setTitle("Gestion des réservations");
        reservationStage.setScene(reservationScene);
        reservationStage.show();

        // Créer et afficher le troisième stage pour la troisième interface
        Stage listeStage = new Stage();
        FXMLLoader listeLoader = new FXMLLoader(MainFx.class.getResource("ListeManagement.fxml"));
        Scene listeScene = new Scene(listeLoader.load(), 600, 400);
        listeStage.setTitle("Liste de gestion");
        listeStage.setScene(listeScene);
        listeStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
