package com.example.werkplekkenfrontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    Scene scene;

    @FXML
    Pane page;

    @FXML
    Pane navBar;

    public ViewController showView(String xmlFileName) {
        ViewController controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/werkplekkenfrontend/" + xmlFileName));
            VBox root = loader.load();
            page.getChildren().setAll(root);
            controller = loader.getController();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return controller;
    }

    public void init(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/werkplekkenfrontend/default.fxml"));
            loader.setController(this);
            VBox root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPopup(Popup popup) {
        popup.show(stage);
    }
}
