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

public class AccountViewController implements ViewController {
    @FXML
    public VBox main_container;
    public Label firstName;
    public Label lastName;
    public Label email;

    private void displayUserInfo() {
        UserDao dao = new UserDao(new HttpService(), new ObjectMapper());
        try {
            User currentUser = dao.getCurrent();
            firstName.setText(currentUser.getName());
            lastName.setText(currentUser.getLastName());
            email.setText(currentUser.getEmail());
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
        }
    }

    public void onLogOutClick() {
        Main.sceneController.showView("login-view.fxml");
    }

    @Override
    public void updateView() {
        displayUserInfo();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
