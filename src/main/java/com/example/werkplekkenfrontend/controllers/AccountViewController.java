package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class AccountViewController implements ViewController{
    @FXML
    public VBox main_container;
    public Label firstName;
    public Label lastName;
    public Label email;

    //private void displayUserInfo(String id){
    //    User user = DaoReplicator.getUserInfoFromID(id);
    //    firstName.setText(user.getName());
     //   lastName.setText(user.getLastName());
    //    email.setText(user.getEmail());
    //}

    public void onLogOutClick() {

    }

    @Override
    public void updateView() {
        //displayUserInfo(UUID.randomUUID().toString());
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
