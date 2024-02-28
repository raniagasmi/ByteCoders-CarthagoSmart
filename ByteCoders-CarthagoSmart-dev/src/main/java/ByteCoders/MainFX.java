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
        // FXMLLoader loader1 = new FXMLLoader((getClass().getResource("/responsable.fxml")));
        FXMLLoader loader2 = new FXMLLoader((getClass().getResource("/gestion_dechets.fxml")));
        //Parent root1 = loader1.load();
        Parent root2 = loader2.load();
        //Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);
        primaryStage.setTitle("CarthagoSmart");
        //primaryStage.setScene(scene1);
        primaryStage.setScene(scene2);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
