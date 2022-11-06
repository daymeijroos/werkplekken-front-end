package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.UUID;

public class AdminNewWorkspaceViewController implements ViewController {

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    public UUID spaceID = null;

    @FXML
    public TextArea capacity;



    private void updateWorkspaceDetails(Space space){
        capacity.setText(String.valueOf(space.getCapacity()));
    }
    @Override
    public void updateView() {

        if (spaceID == null){
            Space newSpace = new Space(Integer.valueOf(capacity.getText())); // there is a chance this generates a duplicate UUID
            System.out.println("Post request response: " + spaceDao);
        }

    }

    @FXML
    void onApplyClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.updateView();

    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.updateView();

    }

}
