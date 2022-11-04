package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.DaoReplicator;
import com.example.werkplekkenfrontend.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class AccountViewController implements ViewController{
    @FXML
    public VBox main_container;
    public Label firstName;
    public Label lastName;
    public Label email;

    private void displayUserInfo(UUID id){
        User user = DaoReplicator.getUserInfoFromID(id);
        firstName.setText(user.getName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }

    public void onLogOutClick() {
        ViewController controller = Main.sceneController.showView("login-view.fxml");
        controller.updateView();
    }

    @Override
    public void updateView() {
        displayUserInfo(UUID.randomUUID());
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
