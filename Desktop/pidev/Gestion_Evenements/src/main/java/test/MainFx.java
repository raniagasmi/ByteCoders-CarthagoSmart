package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFx.class.getResource("EventManagement.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Gestion des événements");
        stage.setScene(scene);
        stage.show();
        Stage reservationStage = new Stage();
        FXMLLoader reservationLoader = new FXMLLoader(MainFx.class.getResource("ReservationManagement.fxml"));
        Scene reservationScene = new Scene(reservationLoader.load(), 600, 400);
        reservationStage.setTitle("Gestion des réservations");
        reservationStage.setScene(reservationScene);
        reservationStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}