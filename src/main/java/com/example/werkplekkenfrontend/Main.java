package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.*;
import com.example.werkplekkenfrontend.models.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static SceneController sceneController = new SceneController();
    public static Stage publicStage;
    public static LoginController loginController = new LoginController();

    @Override
    public void start(Stage stage) {
        loginController.register("day", "meijroos", "daymeijroos@gmail.com", "DumbShit");
        publicStage = stage;
        Main.sceneController.setStage(stage);
        ViewController controller = sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
        // testAdminBuildingsViewController();
        // testAdminEditBuildingViewController();
    }

    private void testAdminBuildingsViewController(){
        AdminBuildingsViewController controller = (AdminBuildingsViewController)Main.sceneController.showView("admin-buildings-view.fxml");
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