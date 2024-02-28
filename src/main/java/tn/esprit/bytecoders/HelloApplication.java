package tn.esprit.bytecoders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.bytecoders.Entity.Facture;
import tn.esprit.bytecoders.Entity.TypeFacture;
import tn.esprit.bytecoders.Services.FacturesService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {


        /*
        FacturesService facturesService = new FacturesService();

        Facture facture = new Facture(
                "libelle",
                new Date(2021, 12, 12),
                new Date(2021, 12, 12),
                100.2,
                TypeFacture.EAU,
                false,
                1
        );

        facturesService.create(facture);

*/



       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factures.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 520);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}