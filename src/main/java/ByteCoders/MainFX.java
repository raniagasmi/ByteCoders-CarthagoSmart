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
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/responsable_dechets.fxml")));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_dechets.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendrier_dechets.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionDECHETS.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation_dechets.fxml"));
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/userMngmt.fxml")));
         //FXMLLoader loader = new FXMLLoader((getClass().getResource("/Afficher.fxml")));
       // FXMLLoader loader = new FXMLLoader((getClass().getResource("/Login.fxml")));
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/EditUser.fxml")));
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/MDP.fxml")));

        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/Code.fxml")));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Accueil.fxml"));
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/EventManagement.fxml")));
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/ReservationManagement.fxml")));
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/SignInUp.fxml")));
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/menuPrincipal.fxml")));
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
