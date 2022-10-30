package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.AdminBuildingsViewController;
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

        AdminBuildingsViewController controller = (AdminBuildingsViewController)Main.sceneController.showView("admin-buildings-view.fxml");
        controller.setText("Dumb bitch");
        controller.updateView();
    }

    public static void main(String[] args) {
        Main.sceneController = new SceneController();
        launch();
    }
}