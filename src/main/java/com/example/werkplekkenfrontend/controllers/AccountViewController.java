package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.DaoReplicator;
import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class AccountViewController implements ViewController {
    @FXML
    public VBox main_container;
    public Label firstName;
    public Label lastName;
    public Label email;
    private User currentUser = null;

    private void displayUserInfo(String id){
        UserDao dao = new UserDao(new HttpService(), new ObjectMapper());
        currentUser = dao.getCurrent();

        firstName.setText(currentUser.getName());
        lastName.setText(currentUser.getLastName());
        email.setText(currentUser.getEmail());

        System.out.println(currentUser);
        System.out.println("current mf: " + currentUser.getName());
    }

    public void onLogOutClick() {

    }

    @Override
    public void updateView() {
        displayUserInfo(UUID.randomUUID().toString());
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
