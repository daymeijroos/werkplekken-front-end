package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.elements.AdminWorkspaceElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Space;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminWorkspaceViewController implements ViewController{

    @FXML
    public VBox workspaces_container;

    @FXML
    public VBox main_container;

    @FXML
    void OnNewWorkspaceButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-new-workspace.fxml");
        controller.updateView();
    }

    private void showSpacesOnView(List<Space> spaces){
        for(Space space : spaces){
            AdminWorkspaceElement element = new AdminWorkspaceElement(this,space);
            workspaces_container.getChildren().add(element.getWorkspaceBox());
        }
    }

    @FXML
    public void onEditWorkspaceButtonClick(Space space) {
        ViewController controller = Main.sceneController.showView("space-edit-workspace.fxml");
        controller.updateView();

    }


    @Override
    public void updateView() {
        List<Space> spacesFromDao = setupTestData();
        showSpacesOnView(spacesFromDao);
        main_container.getChildren().add(new NavBarElement().getBuildingBox());

    }

    public List<Space> setupTestData(){
        List<Space> testSpaces = new ArrayList<>();
        Space werkplek_1 = new Space(UUID.randomUUID(), 1,"werkplek_1");
        testSpaces.add(werkplek_1);
        return testSpaces;
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
