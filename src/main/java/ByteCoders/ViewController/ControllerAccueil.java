package ByteCoders.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAccueil {


    @FXML
    private ImageView backgroundImage;

    @FXML
    private HBox topMenuButtons;

    @FXML
    private VBox mainText;

    @FXML
    private HBox actionButtons;

    @FXML
    private Button contactButton;

    @FXML
    private Button aboutUsButton;

    @FXML
    private Text mainTitle;

    @FXML
    private Text subTitle;

    @FXML
    private Pane sideMenuPane;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button homeButton;

    @FXML
    private Button wasteButton;

    @FXML
    private Button invoiceButton;

    @FXML
    private Button eventButton;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        // Vous pouvez initialiser les éléments du contrôleur ici
        // Par exemple, vous pouvez ajouter des gestionnaires d'événements aux boutons, etc.
        // Exemple :
        contactButton.setOnAction(event -> {
            // Logique pour gérer l'événement du bouton de contact
        });

        // Ajoutez des initialisations supplémentaires selon vos besoins
    }
    @FXML
    private void gestiondechets(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gestionDECHETS.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    @FXML
    private void gestionfacture(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/menuPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    @FXML
    private void gestionevent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ReservationManagement.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }


}
