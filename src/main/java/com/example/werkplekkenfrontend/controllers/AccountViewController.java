package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AccountViewController implements ViewController{
    @FXML
    public VBox main_container;
    public Label firstName;
    public Label lastName;
    public Label email;

    private void displayUserInfo() {
        UserDao dao = new UserDao(new HttpService(), new ObjectMapper());
        User currentUser = dao.getCurrent();

        firstName.setText(currentUser.getName());
        lastName.setText(currentUser.getLastName());
        email.setText(currentUser.getEmail());
    }

    public void onLogOutClick() {
        LoginController controller = (LoginController) Main.sceneController.showView("login-view.fxml");
        controller.setAuthController(Main.authController);
    }

    @Override
    public void updateView() {
        displayUserInfo();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
