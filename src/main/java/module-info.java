module com.example.werkplekkenfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.json;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.werkplekkenfrontend to javafx.fxml;
    exports com.example.werkplekkenfrontend;
    exports com.example.werkplekkenfrontend.controllers;
    exports com.example.werkplekkenfrontend.models;
    exports com.example.werkplekkenfrontend.elements;
    exports com.example.werkplekkenfrontend.daos;
    exports com.example.werkplekkenfrontend.services;
    opens com.example.werkplekkenfrontend.models to com.fasterxml.jackson.databind;
    opens com.example.werkplekkenfrontend.controllers to javafx.fxml;
}
