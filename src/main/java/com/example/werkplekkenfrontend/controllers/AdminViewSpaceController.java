package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AdminViewSpaceController implements ViewController {

    @FXML
    public VBox main_container;
    @FXML
    void OnMeetingroomButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-meetingroom-view.fxml");
        controller.updateView();

    }

    @FXML
    void OnWorkspaceButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-workspace-view.fxml");
        controller.updateView();
    }

    @Override
    public void updateView() {
        main_container.getChildren().add(new NavBarElement().getBuildingBox());

    }


    public void OnNewWorkspaceButtonClick(ActionEvent actionEvent) {
        ViewController controller = Main.sceneController.showView("space-view-new-workspace.fxml");
        controller.updateView();
    }

    public void OnNewMeetingroomButtonClick(ActionEvent actionEvent) {
        ViewController controller = Main.sceneController.showView("space-view-new-meetingroom.fxml");
        controller.updateView();
    }
}
