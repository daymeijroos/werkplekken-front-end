package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminNewWorkspaceViewController implements ViewController {

    @Override
    public void updateView() {

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

}
