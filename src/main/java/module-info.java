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

    opens test to javafx.fxml;
    exports test;
    exports controllers;
    opens controllers to javafx.fxml;

    opens models to javafx.base;

}