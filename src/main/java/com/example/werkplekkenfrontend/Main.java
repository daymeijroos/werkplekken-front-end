package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static SceneController sceneController = new SceneController();

    @Override
    public void start(Stage stage) {
        Main.sceneController.setStage(stage);

        //testAdminBuildingsViewController();
        testAdminEditBuildingViewController();
    }

    private void testAdminBuildingsViewController(){
        AdminBuildingsViewController controller = (AdminBuildingsViewController)Main.sceneController.showView("admin-buildings-view.fxml");
        controller.setText("Dumb bitch");
        controller.updateView();
    }

    private void testAdminEditBuildingViewController(){
        AdminEditBuildingViewController controller = (AdminEditBuildingViewController)Main.sceneController.showView("admin-edit-building-view.fxml");
        controller.updateView();
    }

    public static void main(String[] args) {
        Main.sceneController = new SceneController();
        launch();
    }
}