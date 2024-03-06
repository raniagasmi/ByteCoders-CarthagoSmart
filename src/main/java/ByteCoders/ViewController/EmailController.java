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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

public class EmailController {
    @FXML
    private Button mail1;
    @FXML
    private Button mail3;
    @FXML
    private Button mail2;


        // Adresse e-mail statique
        private static final String FROM_EMAIL = "sarra.rejeb@esprit.tn";
        private static final String PASSWORD = "Sadokhou2212@@";

        // Méthode pour envoyer un e-mail
        public void sendEmail1(String toEmail) {
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
                message.setSubject("Ramassage d'encombrement");
                message.setText("Merci de coordonner le ramassage des objets encombrants devant ma résidence, selon les modalités prévues.");

                Transport.send(message);

                System.out.println("E-mail envoyé avec succès à : " + toEmail);
            } catch (MessagingException e) {
                System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            }
        }

        // Fonction onAction pour le bouton d'envoi d'e-mail
        @FXML
        private void sendButtonClicked1(ActionEvent event) {
            // Définir l'adresse e-mail de destination
            String toEmail = "sarra.rejeb@esprit.tn";

            // Appeler la méthode d'envoi d'e-mail
            sendEmail1(toEmail);
            try {
                Notifications notificationBuilder = Notifications.create()
                        .title("Notification")
                        .text("Réclamation bien reçu !")
                        .graphic(null)
                        .hideAfter(javafx.util.Duration.seconds(10)) // Notification disappears after 5 seconds
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(actionEvent -> System.out.println("Réclamation !"));
                notificationBuilder.showConfirm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void sendEmail2(String toEmail) {
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
            message.setSubject("Réclamer une incivilité \n");
            message.setText("Merci de prendre les mesures nécessaires pour résoudre rapidement la situation des déchets non conformes observés.");

            Transport.send(message);

            System.out.println("E-mail envoyé avec succès à : " + toEmail);
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }

    // Fonction onAction pour le bouton d'envoi d'e-mail
    @FXML
    private void sendButtonClicked2(ActionEvent event) {
        // Définir l'adresse e-mail de destination
        String toEmail = "sarra.rejeb@esprit.tn";

        // Appeler la méthode d'envoi d'e-mail
        sendEmail2(toEmail);

        try {
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification")
                    .text("Réclamation bien reçu !")
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(10)) // Notification disappears after 5 seconds
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(actionEvent -> System.out.println("Réclamation !"));
            notificationBuilder.showConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendEmail3(String toEmail) {
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
            message.setSubject("Demande de Bac de TRI");
            message.setText("Merci de m'attribuer un bac de tri pour faciliter la gestion des déchets à mon domicile.");

            Transport.send(message);

            System.out.println("E-mail envoyé avec succès à : " + toEmail);
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }

    // Fonction onAction pour le bouton d'envoi d'e-mail
    @FXML
    private void sendButtonClicked3(ActionEvent event) {
        // Définir l'adresse e-mail de destination
        String toEmail = "sarra.rejeb@esprit.tn";

        // Appeler la méthode d'envoi d'e-mail
        sendEmail3(toEmail);
        try {
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification")
                    .text("Réclamation bien reçu !")
                    .graphic(null)
                    .hideAfter(javafx.util.Duration.seconds(10)) // Notification disappears after 5 seconds
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(actionEvent -> System.out.println("Réclamation !"));
            notificationBuilder.showConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

