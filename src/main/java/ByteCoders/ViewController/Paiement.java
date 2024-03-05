package ByteCoders.ViewController;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import org.controlsfx.control.Notifications;

public class Paiement {
    @FXML
    private void handlePayment(ActionEvent event) throws SQLException, IOException {
        showNotification("Paiement effectué avec succès", "Payment has been added successfully");

}
    private void showNotification(String title, String message){
        Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .show();
    }
}
