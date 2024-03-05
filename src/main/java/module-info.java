module test {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.mail;
    requires com.google.zxing;
    requires java.desktop;
    requires com.google.zxing.javase;
    requires activation;
    requires org.apache.pdfbox;
<<<<<<< HEAD
    requires javafx.swing;
=======
>>>>>>> 778c81c01ff00e182c43909c38ef7e747f667aea

    opens test to javafx.fxml;
    exports test;
    exports controllers;
    opens controllers to javafx.fxml;

    opens models to javafx.base;

}