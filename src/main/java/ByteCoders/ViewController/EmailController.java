package ByteCoders.ViewController;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmailController {
    @FXML
    private Button mail;;


        // Adresse e-mail statique
        private static final String FROM_EMAIL = "sarra.rejeb@esprit.tn";
        private static final String PASSWORD = "Sadokhou2212@@";

        // Méthode pour envoyer un e-mail
        public void sendEmail(String toEmail) {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.office365.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM_EMAIL));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject("Sujet de l'e-mail");
                message.setText("Contenu de l'e-mail");

                Transport.send(message);

                System.out.println("E-mail envoyé avec succès à : " + toEmail);
            } catch (MessagingException e) {
                System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            }
        }

        // Fonction onAction pour le bouton d'envoi d'e-mail
        @FXML
        private void sendButtonClicked(ActionEvent event) {
            // Définir l'adresse e-mail de destination
            String toEmail = "sarra.rejeb@esprit.tn";

            // Appeler la méthode d'envoi d'e-mail
            sendEmail(toEmail);
        }



    @FXML
    public void retourEnArriére(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gestionDECHETS.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    }

