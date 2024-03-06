package ByteCoders;

import ByteCoders.Model.Facture;
import ByteCoders.Model.TypeFacture;
import ByteCoders.Service.FacturesService;
import ByteCoders.ViewController.CreditCardValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {

         /*
         FacturesService facturesService = new FacturesService();

        for (int i = 0; i < 40; i++) {
            Facture facture = new Facture(
                    "libelle" + i,
                    new Date(2001 + (int) (Math.random() * 24), 1 + (int) (Math.random() * 12), 1 + (int) (Math.random() * 28)),
                    new Date(2001 + (int) (Math.random() * 24), 1 + (int) (Math.random() * 12), 1 + (int) (Math.random() * 28)),
                    Math.random() * 1000,
                    // random type
                    TypeFacture.values()[(int) (Math.random() * TypeFacture.values().length)],
                    Math.random() > 0.5,
                    1
            );
            facturesService.create(facture);
        }
         * */

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paiementCopie.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 820, 480);
        primaryStage.setTitle("CarthagoSmart");
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    private void generatePDF(int montant, String mpaiement) throws IOException {

        /*
                try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Paiement Details");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Montant: " + montant);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Mode de Paiement: " + mpaiement);
                contentStream.endText();
            }

            // Specify the directory where you want to save the PDF file
            String pdfFilePath = "Paiement_" + System.currentTimeMillis() + ".pdf";

            // Save the PDF file
            document.save(pdfFilePath);

            // Open the generated PDF file with the default PDF viewer
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(pdfFilePath));
            } else {
                System.out.println("Desktop is not supported on this platform.");
            }
        }
        * */
    }


    public static void main(String[] args) {
        launch();
    }
}