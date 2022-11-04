module com.example.werkplekkenfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.json;
    requires java.net.http;

    opens com.example.werkplekkenfrontend to javafx.fxml;
    exports com.example.werkplekkenfrontend;
    exports com.example.werkplekkenfrontend.controllers;
    exports com.example.werkplekkenfrontend.models;
    exports com.example.werkplekkenfrontend.elements;
    exports com.example.werkplekkenfrontend.daos;
    opens com.example.werkplekkenfrontend.controllers to javafx.fxml;
}