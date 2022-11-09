package com.example.werkplekkenfrontend.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    Scene scene;

    public ViewController showView(String xmlFileName) {
        ViewController controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/werkplekkenfrontend/" + xmlFileName));
            VBox root = loader.load();
            scene.setRoot(root);
            controller = loader.getController();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return controller;
    }

    public void init(Stage stage) {
        this.stage = stage;
        scene = new Scene(new VBox());
        stage.setScene(scene);
    }

    public void showPopup(Popup popup) {
        popup.show(stage);
    }
}
