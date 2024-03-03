package tn.esprit.bytecoders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.bytecoders.Entity.Facture;
import tn.esprit.bytecoders.Entity.TypeFacture;
import tn.esprit.bytecoders.Services.FacturesService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {

        FacturesService facturesService = new FacturesService();

        /*
        for (int i = 0; i < 40; i++) {
            Facture facture = new Facture(
                    "libelle" + i,
                    new Date(2001 + (int) (Math.random() * 24), 1 + (int) (Math.random() * 12), 1 + (int) (Math.random() * 28)),
                    new Date(2001 + (int) (Math.random() * 24), 1 + (int) (Math.random() * 12), 1 + (int) (Math.random() * 28)),
                    Math.random() * 1000,
                    // random type
                    TypeFacture.values()[(int) (Math.random() * TypeFacture.values().length)],
                    Math.random() > 0.5,
                    1
            );
            facturesService.create(facture);
        }
         */

       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factureEAU.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 620, 480);
        primaryStage.setTitle("CarthagoSmart");
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}