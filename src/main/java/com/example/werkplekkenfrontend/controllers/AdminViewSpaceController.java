package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminViewSpaceController implements ViewController {

    @FXML
    void OnNewMeetingroomButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-edit-meetingroom.fxml");
        controller.updateView();

    }

    @FXML
    void OnNewWorkspaceButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-edit-workspace.fxml");
        controller.updateView();


    }

    @FXML
    void onEditMeetingRoomButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-edit-meetingroom.fxml");
        controller.updateView();

    }

    @FXML
    void onEditWorkspaceButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-edit-workspace.fxml");
        controller.updateView();

    }
    @Override
    public void updateView() {

    }
}
