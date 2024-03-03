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


        /*
        FacturesService factureService = new FacturesService();

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




       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factureEAU.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("CarthagoSmart");
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();






        /*Scene scene = new Scene(fxmlLoader.load(), 820, 520);
        stage.setTitle("Carthago-Smart");
        stage.setScene(scene);
        stage.show();*/

        /*FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("menuPrincipal.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 820, 520);
        stage.setTitle("Carthago-Smart");
        stage.setScene(scene1);
        stage.show();

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("factureEAU.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 820, 520);
        stage.setTitle("Cartago-Smart");
        stage.setScene(scene2);
        stage.show();

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("Consommation.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 820, 520);
        stage.setTitle("Carthago-Smart");
        stage.setScene(scene3);
        stage.show(); */


    }

    public static void main(String[] args) {
        launch();
    }
}