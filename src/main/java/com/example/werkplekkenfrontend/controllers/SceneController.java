package com.example.werkplekkenfrontend.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneController {
    public SceneController(Stage stage) {
        this.stage = stage;
    }

    private final Stage stage;

    public ViewController showView(String xmlFileName) {
        ViewController controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/werkplekkenfrontend/" + xmlFileName));
            VBox root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            controller = loader.getController();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return controller;
    }
}
