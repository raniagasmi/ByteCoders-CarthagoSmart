module ByteCoders {
    requires java.sql;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;
    requires com.gluonhq.maps;


    opens ByteCoders to javafx.fxml;

    opens ByteCoders.ViewController to javafx.fxml;
    opens ByteCoders.Model to javafx.base;

    exports ByteCoders;


}