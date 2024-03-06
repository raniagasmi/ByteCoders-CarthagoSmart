package ByteCoders.ViewController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

public class Notification {
    @FXML
        private void sendReclamation() {
            try {
                Notifications notificationBuilder = Notifications.create()
                        .title("Notification")
                        .text("Réclamtion envoyéé avec succés")
                        .graphic(null)
                        .hideAfter(javafx.util.Duration.seconds(10)) // Notification disappears after 5 seconds
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(actionEvent -> System.out.println("Réclamation !"));
                notificationBuilder.showConfirm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

