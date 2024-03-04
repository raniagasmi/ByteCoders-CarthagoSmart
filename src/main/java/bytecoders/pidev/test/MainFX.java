package bytecoders.pidev.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainFX extends Application {
  /*  double x;
    double y = 0.0;*/

    @Override
    public void start(Stage primaryStage) throws IOException {
       // FXMLLoader loader = new FXMLLoader((getClass().getResource("/userMngmt.fxml")));
     // FXMLLoader loader = new FXMLLoader((getClass().getResource("/Afficher.fxml")));
      FXMLLoader loader = new FXMLLoader((getClass().getResource("/Login.fxml")));
        //FXMLLoader loader = new FXMLLoader((getClass().getResource("/EditUser.fxml")));
      // FXMLLoader loader = new FXMLLoader((getClass().getResource("/MDP.fxml")));
       // FXMLLoader loader = new FXMLLoader((getClass().getResource("/Code.fxml")));
       // FXMLLoader loader = new FXMLLoader((getClass().getResource("/sample.fxml")));
     //  FXMLLoader loader = new FXMLLoader((getClass().getResource("/sample.fxml")));
       // FXMLLoader loader = new FXMLLoader((getClass().getResource("/bytecoders/pidev/main.fxml")));


       //FXMLLoader loader = new FXMLLoader((getClass().getResource("/bytecoders/pidev/EditUser.fxml")));
       // FXMLLoader loader = new FXMLLoader((getClass().getResource("/Afficher.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("CarthagoSmart");
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

     /*   Parent root = FXMLLoader.load(this.getClass().getResource("/sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed((event) -> {
            this.x = event.getSceneX();
            this.y = event.getSceneY();
        });
        root.setOnMouseDragged((event) -> {
            primaryStage.setX(event.getScreenX() - this.x);
            primaryStage.setY(event.getScreenY() - this.y);
        });
        primaryStage.setScene(new Scene(root, 700.0, 400.0));
        primaryStage.show();*/
    }
    public static void main(String[] args) {
        launch(args);
    }

}
