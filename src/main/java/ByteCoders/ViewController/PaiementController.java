package ByteCoders.ViewController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.Getter;

public class PaiementController {

    @FXML
    private TextField Montant;

    @FXML
    private TextField Mpaiement;

    @FXML
    private TextField numérodelacarte;

    @FXML
    private Button payButton;

    @Getter
    private String factureId;

    @FXML
    private void createP(ActionEvent event) {
        try {
            Stripe.apiKey = "sk_test_51OpYc6CgjBGw1mqPMm9JLvqAwMmjMLT286AdPq7JqiS0IEXWD82gibIN06nbSMZDqMxFpeGRgDiDISwIZT6I1GVo00K1eK22ei";

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setCurrency("usd")
                    .setAmount(1000L)
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Paiement:");
            alert.setHeaderText(null);
            alert.setContentText("Paiement effectué avec succés. PaymentIntent ID: " + intent.getId());
            alert.showAndWait();


        } catch (StripeException e) {
            System.out.println("Payment failed. Error: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Paiement:");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Paiement échoué. Error: " + e.getMessage());
            alert.showAndWait();
        }

    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
