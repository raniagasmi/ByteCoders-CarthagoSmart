module test {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens test to javafx.fxml;
    exports test;
    exports controllers;
    opens controllers to javafx.fxml;

    opens models to javafx.base;

}