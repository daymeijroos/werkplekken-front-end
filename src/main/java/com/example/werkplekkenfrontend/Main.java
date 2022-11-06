package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.*;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.UUID;

public class Main extends Application {
    public static SceneController sceneController = new SceneController();
    public static Stage publicStage;
    public static LoginController loginController = new LoginController(new LoginDao(new HttpService(), new ObjectMapper()), new UserDao(new HttpService(), new ObjectMapper()));


    @Override
    public void start(Stage stage) {
        SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
        loginController.register("day", "meijroos", "daymeijroos4@gmail.com", "DumbShit");
        spaceDao.post(new Space(2));
        publicStage = stage;
        Main.sceneController.setStage(stage);
        ViewController controller = sceneController.showView("admin-workspace-view.fxml");
        controller.updateView();
        // testAdminBuildingsViewController();
        // testAdminEditBuildingViewController();
    }

    /*private void testAdminBuildingsViewController(){
        AdminBuildingsViewController controller = (AdminBuildingsViewController)Main.sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
    }

     */

    /*private void testAdminEditBuildingViewController(){
        AdminEditBuildingViewController controller = (AdminEditBuildingViewController)Main.sceneController.showView("admin-edit-building-view.fxml");
        controller.updateView();
    }
     */

    public static void main(String[] args) {
        Main.sceneController = new SceneController();
        launch();
    }
}