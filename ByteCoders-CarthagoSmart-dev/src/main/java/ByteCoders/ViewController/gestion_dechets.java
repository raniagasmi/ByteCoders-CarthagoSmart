package ByteCoders.ViewController;

import ByteCoders.Model.Categorie;
import ByteCoders.Model.collect;
import ByteCoders.Service.CollectDechet;
import ByteCoders.Service.MyDB;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import com.gluonhq.maps.MapView;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapLayer;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.net.MalformedURLException;
import ByteCoders.ViewController.Responsable;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.geometry.Pos;
public class gestion_dechets {
    //////////
    @FXML
    public Button accueil;
    @FXML
    public Button déchets;
    @FXML
    public Button facture;
    @FXML
    public Button evenements;
    @FXML
    public ImageView logo;
    @FXML
    public ImageView account;
    ////////
    FXMLLoader loader = new FXMLLoader(getClass().getResource("gestion-dechets.fxml"));
    @FXML
    private ComboBox<String> TypeDechetsComboBox;

    @FXML
    private TextField positionField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label collectionStatusLabel;

    @FXML
    private Button VerifierSIButton;


    @FXML
    private Button findRecyclageButton;
    private static final CollectPoint[] collectPoints = {
            new CollectPoint("Point 1", TypeDechet.NON_RECYCLABLE, DayOfWeek.MONDAY),
            new CollectPoint("Point 2", TypeDechet.NON_RECYCLABLE, DayOfWeek.WEDNESDAY),
            new CollectPoint("Point 3", TypeDechet.NON_RECYCLABLE, DayOfWeek.FRIDAY),
            new CollectPoint("Point 4", TypeDechet.NON_RECYCLABLE, DayOfWeek.SATURDAY),

    };

    private TypeDechet convertStringToTypeDechet(String str) {
        if ("Non Recyclable".equals(str)) {
            return TypeDechet.NON_RECYCLABLE;
        } else if ("Recyclable".equals(str)) {
            return TypeDechet.RECYCLABLE;
        } else {
            return null;
        }
    }

    private boolean checkCollectionDay(LocalDate selectedDate, TypeDechet Typedechets) {
        for (CollectPoint collectPoint : collectPoints) {
            if (collectPoint.getTypeDechets() == Typedechets &&
                    selectedDate.getDayOfWeek() == collectPoint.getCollectDay()) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void handleFindRecyclage(ActionEvent event) {
        //findRecyclageButton.setVisible(false);
        adresse.setVisible(true);

        if (!isMapViewVisible) {
            adresse.setVisible(true);
        }
    }

    @FXML
    private void handleVerifierSI(ActionEvent event) {
        String TypedechetsStr = TypeDechetsComboBox.getValue();
        TypeDechet Typedechets = convertStringToTypeDechet(TypedechetsStr);
        LocalDate selectedDate = datePicker.getValue();

        if (Typedechets == TypeDechet.RECYCLABLE) {
            collectionStatusLabel.setText("Veuillez Rechercher un point de recyclage proche de vous.");
            findRecyclageButton.setVisible(true);
        } else {
            findRecyclageButton.setVisible(false);

            if (Typedechets == TypeDechet.NON_RECYCLABLE && selectedDate != null) {
                boolean isCollectionDay = checkCollectionDay(selectedDate, Typedechets);

                if (isCollectionDay) {
                    collectionStatusLabel.setText("C'est un jour de ramassage.");
                } else {
                    collectionStatusLabel.setText("Ce n'est pas un jour de ramassage.");
                }
            } else {
                if (Typedechets == null) {
                    collectionStatusLabel.setText("Sélection de type de déchet invalide.");
                } else if (selectedDate == null) {
                    collectionStatusLabel.setText("Aucune date sélectionnée.");
                }
            }
        }
    }

    ///********MAP************//
    private boolean isMapViewVisible = false;

    @FXML
    private VBox adresse;


    @FXML
    private Button findLocationsButton;

    private final MapPoint Tunisia = new MapPoint(36.80123306920609, 10.216339046924556);

    public void initialize() {
        // Désactive l'affichage initial du bouton de recyclage
        findRecyclageButton.setVisible(false);
        // Désactive l'affichage initial de la carte
        adresse.setVisible(false);
        //adresse.setManaged(false); // Ne prend pas d'espace s'il est invisible
        MapView mapView = createMapView();
        adresse.getChildren().add(mapView);
        VBox.setVgrow(mapView, Priority.ALWAYS);
    }

    @FXML
    private void handleFindLocations(ActionEvent event) {
        String TypedechetsStr = TypeDechetsComboBox.getValue();
        TypeDechet Typedechets = convertStringToTypeDechet(TypedechetsStr);

        // Vérifie si aucun type de déchet n'est sélectionné
        if (Typedechets == null) {
            collectionStatusLabel.setText("Sélection de type de déchet invalide.");
            return;  // Arrête la fonction ici pour éviter de continuer avec un type de déchet invalide
        }

        if (Typedechets == TypeDechet.RECYCLABLE) {
            collectionStatusLabel.setText("Veuillez Rechercher un point de recyclage proche de vous.");
            findRecyclageButton.setVisible(true);
        } else {
            // Masque la carte si elle n'est pas déjà visible
            adresse.setVisible(true);

            if (!isMapViewVisible) {
                adresse.setVisible(true);
                isMapViewVisible = true;
            }
        }
    }

    @FXML
    private MapView createMapView() {
        MapView mapView = new MapView();
        mapView.setPrefSize(100, 50);
        mapView.addLayer(new CustomMapLayer());
        mapView.setZoom(10);
        mapView.flyTo(0, Tunisia, 0.1);
        return mapView;
    }


    public class CustomMapLayer extends MapLayer {
        private final Node marker;

        public CustomMapLayer() {
            marker = new Circle(8, Color.RED);
            getChildren().add(marker);
        }

        @Override
        protected void layoutLayer() {
            Point2D point = getMapPoint(Tunisia.getLatitude(), Tunisia.getLongitude());
            marker.setTranslateX(point.getX());
            marker.setTranslateY(point.getY());
        }
    }

    @FXML
    void sendReclamation (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/reclamation_dechets.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        ///NOTIFICATION///
        Notifications notifications=Notifications.create();
        notifications.text("Merci pour votre réclamation");
        notifications.title("Notification");
        notifications.hideAfter(Duration.seconds(4));
        notifications.show();
    }

    @FXML
    public Button calendrier;
    @FXML
    public void handleCalendrier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/calendrier_dechets.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    }

    ///NOTIFICATION///
    /*@FXML
    private void user_restore(ActionEvent event) throws SQLException, Exception {
        String userEmail = "sarra.rejeb@esprit.tn";
        Responsable r = new Responsable();
        CollectDechet cd =new CollectDechet();
        Connection connection = null;

        try {
            // Ouvrir une connexion à la base de données
            connection = MyDB.getInstance().getCnx();
            //collect co = r.recuperer().stream().filter(x -> userEmail.equals(x.getAddEmail())).findAny().orElse(null);

            cd.addDechets(new collect("verre", Categorie.RECYCLABLE, "arina", "2024-03-18", "__"));

            if (cd != null) {
                // Envoyer un e-mail à l'utilisateur
                cd.EnvoieMail(userEmail);
                // Charger Code.fxml
              //  loadCodeFXML(event);
            } else {
                // Aucun utilisateur trouvé avec l'adresse e-mail donnée
                System.out.println("Aucun utilisateur trouvé avec l'adresse e-mail donnée.");
            }
        } catch (SQLException e) {
            // Gérer les exceptions SQL
            e.printStackTrace();
        }
    }*/

    /*@FXML
    private void loadCodeFXML(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Code.fxml"));
        Parent root = loader.load();
        CodeController codeController = getController();
        codeController.setUserEmail(userEmail);

        // Passer l'adresse e-mail au contrôleur du code.fxml
        // codeController.setUserEmail(user_email.getText());

        // Afficher la nouvelle scène
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }*/




