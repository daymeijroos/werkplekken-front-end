package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static SceneController sceneController = new SceneController();
    public static Stage publicStage;

    @Override
    public void start(Stage stage) {
        publicStage = stage;
        Main.sceneController.setStage(stage);
        ViewController controller = sceneController.showView("login-view.fxml");
        controller.updateView();
    }

    public static void main(String[] args) {
        Main.sceneController = new SceneController();
        launch();
    }
}