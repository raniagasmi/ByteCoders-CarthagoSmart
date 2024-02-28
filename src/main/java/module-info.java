module tn.esprit.bytecoders {
    requires java.sql;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;


    opens tn.esprit.bytecoders to javafx.fxml;

    opens tn.esprit.bytecoders.controllers to javafx.fxml;
    opens tn.esprit.bytecoders.models to javafx.base;

    exports tn.esprit.bytecoders;

}