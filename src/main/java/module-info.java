module com.example.werkplekkenfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.werkplekkenfrontend to javafx.fxml;
    exports com.example.werkplekkenfrontend;
}