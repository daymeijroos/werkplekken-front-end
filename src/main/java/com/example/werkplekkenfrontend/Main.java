package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.AuthController;
import com.example.werkplekkenfrontend.controllers.SceneController;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.models.CurrentUser;
import com.example.werkplekkenfrontend.services.HttpService;
import com.example.werkplekkenfrontend.views.LoginView;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {

    public static SceneController sceneController = new SceneController();
    public static CurrentUser currentUser = new CurrentUser();

    public static AuthController authController = new AuthController(
            new LoginDao(new HttpService(), new ObjectMapper()),
            new UserDao(new HttpService(), new ObjectMapper())
    );

    public static LoginView loginView;


    public static class Launcher extends Application {


        @Override
        public void start(Stage stage) {
            sceneController.init(stage);
            loginView = (LoginView) sceneController.showView("login-view.fxml");
            loginView.getController().setAuthController(authController);
        }

        public static void main(String[] args) {
            sceneController = new SceneController();
            launch();
        }
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}