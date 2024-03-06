package ByteCoders.ViewController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

public class PaiementCopieController implements Initializable {
    public ImageView card_icon;
    public Image card_icon_image;
    public ImageView card_mastercard_icon;
    public ImageView card_error_icon;
    public ImageView card_american_icon;
    public ImageView card_visa_icon;
    public TextField card_number;
    public TextField month;
    public TextField year;
    public TextField cvc;
    public Button payer;
    public Label ref_fact;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        card_mastercard_icon.setVisible(false);
        card_error_icon.setVisible(false);
        card_american_icon.setVisible(false);
        card_visa_icon.setVisible(false);
        payer.setDisable(true);

        ref_fact.setText(ref_fact.getText() + "reffff");


        card_number.textProperty().addListener((observable, oldVal, newVal) -> {
            CreditCardValidator cardValidator = new CreditCardValidator();
            String cardType = cardValidator.validateCard(newVal);
            if (cardType.contains("visa")) {
                card_visa_icon.setVisible(true);
                card_mastercard_icon.setVisible(false);
                card_american_icon.setVisible(false);
                card_error_icon.setVisible(false);
                payer.setDisable(false);
            } else if (cardType.contains("mastercard")) {
                card_visa_icon.setVisible(false);
                card_mastercard_icon.setVisible(true);
                card_american_icon.setVisible(false);
                card_error_icon.setVisible(false);
                payer.setDisable(false);
            } else if (cardType.contains("american")) {
                card_visa_icon.setVisible(false);
                card_mastercard_icon.setVisible(false);
                card_american_icon.setVisible(true);
                card_error_icon.setVisible(false);
                payer.setDisable(false);
            } else {
                card_visa_icon.setVisible(false);
                card_mastercard_icon.setVisible(false);
                card_american_icon.setVisible(false);
                card_error_icon.setVisible(true);
                payer.setDisable(true);
            }
        });

    }

    @FXML
    private void handlePayment(ActionEvent event) throws SQLException, IOException {
        if (year.getText().length() == 4) {
            try {
                int y = Integer.parseInt(year.getText());
                if (!(y >= 2024 && y <= 2100)) {
                    year.setText("");
                }
            } catch (NumberFormatException e) {
                year.setText("");
            }
        }else {
            year.setText("");
        }
        if (month.getText().length() == 2) {
            try {
                int m = Integer.parseInt(month.getText());
                if (!(m >= 1 && m <= 12)) {
                    month.setText("");
                }
            } catch (NumberFormatException e) {
                month.setText("");
            }
        } else {
            month.setText("");
        }

        try {
            int c = Integer.parseInt(cvc.getText());
        } catch (NumberFormatException e) {
            cvc.setText("");
        }

        this.createP();


    }

    private void showNotification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .show();
    }


    private void createP() {
        try {
            Stripe.apiKey = "sk_test_51OpYc6CgjBGw1mqPMm9JLvqAwMmjMLT286AdPq7JqiS0IEXWD82gibIN06nbSMZDqMxFpeGRgDiDISwIZT6I1GVo00K1eK22ei";

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setCurrency("usd")
                    .setAmount(1000L)
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Status");
            alert.setHeaderText(null);
            alert.setContentText("Payment successful. PaymentIntent ID: " + intent.getId());
            alert.showAndWait();
            showNotification("Payment Status", "Payment successful. PaymentIntent ID: " + intent.getId());


        } catch (StripeException e) {
            System.out.println("Payment failed. Error: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Payment Status");
            alert.setHeaderText(null);
            alert.setContentText("There was an error processing your payment. Please try again.");
            alert.showAndWait();
        }

    }

    @FXML
    public void handleFacture(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/ByteCoders/menuPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
