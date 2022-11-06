package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.UUID;

public class AdminNewMeetingroomViewController implements ViewController {

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    public UUID spaceID = null;

    @Override
    public void updateView() {
    }



    @FXML
    void onApplyClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-meetingroom-view.fxml");
        controller.updateView();

        /*if (spaceID == null){
            Space newSpace = new Space(UUID.randomUUID(),Integer.valueOf(capacity.getText())); // there is a chance this generates a duplicate UUID
            System.out.println("Post request response: " + spaceDao);
        }

         */

    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-meetingroom-view.fxml");
        controller.updateView();

    }
}
