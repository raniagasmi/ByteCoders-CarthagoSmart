package ByteCoders.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jdk.internal.icu.text.UCharacterIterator;
import org.w3c.dom.Document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

public class DetailsFactureController {

        @FXML
        private Label dateEchLabel;

        @FXML
        private Label dateLabel;

        @FXML
        private Label estPayeeLabel;

        @FXML
        private Label libelleLabel;

        @FXML
        private Label montantLabel;

        @FXML
        private Label refLabel;

        @FXML
        private Label typeLabel;




        /*public void genererPDFfromDataBase(ActionEvent actionEvent) throws SQLException {
            Document document = new Document();

            try {
                PdfWriter.getInstance(document, new FileOutputStream("example.pdf"));
                document.open();

                Paragraph paragraph = new Paragraph();
                paragraph.add("Hello, this is a PDF document generated using iText.");

                document.add(paragraph);

                System.out.println("PDF generated successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
        }*/
}