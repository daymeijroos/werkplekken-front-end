package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.models.DaoReplicator;
import com.example.werkplekkenfrontend.models.Space;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.UUID;

public class AdminEditMeetingroomViewController implements ViewController{

    public UUID spaceID = null;


    @FXML
    public TextArea facility;

    @FXML
    public  TextArea capacity;

    @FXML
    public  TextArea name;

    private void updateMeetingroomDetails(Space space){
        name.setText(space.getName());
        capacity.setText(String.valueOf(space.getCapacity()));
        facility.setText(space.getFacilities());

    }


    @FXML
    void onApplyClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-space-view.fxml");
        controller.updateView();

    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-space-view.fxml");
        controller.updateView();

    }

    @Override
    public void updateView() {
            Space spaceFromDao = DaoReplicator.getMeetingroomFromID(UUID.randomUUID());
            updateMeetingroomDetails(spaceFromDao);
    }

}
