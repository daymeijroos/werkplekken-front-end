package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.HelloController;
import com.example.werkplekkenfrontend.controllers.SceneController;
import com.example.werkplekkenfrontend.controllers.ViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static SceneController sceneController = new SceneController();

    @Override
    public void start(Stage stage) {
        Main.sceneController.setStage(stage);

        HelloController controller = (HelloController)Main.sceneController.showView("hello-view.fxml");
        controller.setText("Dumb bitch");
    }

    public static void main(String[] args) {
        Main.sceneController = new SceneController();
        launch();
    }
}