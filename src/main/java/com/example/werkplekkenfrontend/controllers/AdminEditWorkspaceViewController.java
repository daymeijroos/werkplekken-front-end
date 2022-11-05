package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.models.DaoReplicator;
import com.example.werkplekkenfrontend.models.Space;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.UUID;

public class AdminEditWorkspaceViewController implements ViewController{

    @FXML
    private TextArea capacity;

    @FXML
    private TextArea name;



    private void updateWorkspaceDetails(Space space){
        name.setText(space.getName());
        capacity.setText(String.valueOf(space.getCapacity()));

    }
    @FXML
    void onApplyClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-workspace-view.fxml");
        controller.updateView();

    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-workspace-view.fxml");
        controller.updateView();
    }

    @Override
    public void updateView() {
        Space spaceFromDao = DaoReplicator.getWorkSpaceFromID(UUID.randomUUID());
        updateWorkspaceDetails(spaceFromDao);

    }
}
