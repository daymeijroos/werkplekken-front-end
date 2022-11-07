package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.*;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.models.CurrentUser;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static SceneController sceneController = new SceneController();
    public static CurrentUser currentUser = new CurrentUser();

    public AuthController authController = new AuthController(
            new LoginDao(new HttpService(), new ObjectMapper()),
            new UserDao(new HttpService(), new ObjectMapper())
    );

    LoginController loginController;

    @Override
    public void start(Stage stage) {
        Main.sceneController.init(stage);
        loginController = (LoginController) Main.sceneController.showView("login-view.fxml");
        loginController.setAuthController(authController);
    }

    public static void main(String[] args) {
        Main.sceneController = new SceneController();
        launch();
    }
}