package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.HelloController;
import com.example.werkplekkenfrontend.controllers.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    SceneController sceneController;

    @Override
    public void start(Stage stage) {
        this.sceneController = new SceneController(stage);
        HelloController controller = (HelloController)this.sceneController.showView("hello-view.fxml");
        controller.setText("Dumb bitch");
    }

    public static void main(String[] args) {
        launch();
    }
}