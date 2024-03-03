package ByteCoders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/responsable_dechets.fxml")));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_dechets.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendrier_dechets.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionDECHETS.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("CarthagoSmart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
