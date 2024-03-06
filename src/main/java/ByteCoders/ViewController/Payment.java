package ByteCoders.ViewController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Payment {
    @FXML
    private Label accountInfoLabel;

    public Payment(Label accountInfoLabel) {
        this.accountInfoLabel = accountInfoLabel;
    }

    @FXML
    private void retrieveAccountInfo() {
        try {
            // Your account retrieval logic here
            Account account = Account.retrieve();
            accountInfoLabel.setText("Account ID: " + account.getId());
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
// Set your secret key here
        Stripe.apiKey = "sk_test_51OpGXoBSHXNDJBCJpZtYJsUSzkGbzkbzeW9KnL1QBitIxv88KMKJwvefU8Uc7F0eWtUqyMqIYxN9h9JG3NFT90nr003COdR8fE";

        try {
// Retrieve your account information
            Account account = Account.retrieve();
            System.out.println("Account ID: " + account.getId());
// Print other account information as needed
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

}


