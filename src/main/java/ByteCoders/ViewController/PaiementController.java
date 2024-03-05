package ByteCoders.ViewController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PaiementController {

    @FXML
    private TextField Montant;

    @FXML
    private TextField Mpaiement;

    @FXML
    private TextField numérodelacarte;

    @FXML
    private Button payButton;

    @FXML
    private void createP(ActionEvent event) {
        try {
            showAlert("Payment successful.");
// Set your secret key here
            Stripe.apiKey = "sk_test_51OpYc6CgjBGw1mqPMm9JLvqAwMmjMLT286AdPq7JqiS0IEXWD82gibIN06nbSMZDqMxFpeGRgDiDISwIZT6I1GVo00K1eK22ei";

// Create a PaymentIntent with other payment details
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(1000L) // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

// If the payment was successful, display a success message
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
// If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
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
