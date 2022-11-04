package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class LoginViewController implements ViewController{
    @FXML
    public VBox main_container;
    public TextArea email;
    public TextArea password;
    public TextArea forgotPassword;

    private final String RESTApiUrl = "http://localhost:8081/auth/login";

    public void onSignInClick(ActionEvent actionEvent) {
        ViewController controller = Main.sceneController.showView("admin-view.fxml");
        controller.updateView();
    }

    @Override
    public void updateView() {}
}
