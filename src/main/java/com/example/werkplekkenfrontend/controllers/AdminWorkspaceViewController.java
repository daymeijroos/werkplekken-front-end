package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.elements.AdminWorkspaceElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AdminWorkspaceViewController implements ViewController {

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    public String floorId;

    @FXML
    public VBox workspaces_container;

    @FXML
    public VBox main_container;

    @FXML
    void OnNewWorkspaceButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-edit-workspace.fxml");
        controller.updateView();
    }

    private void showSpacesOnView() {
        List<Space> spacesFromDao = spaceDao.getAll();
        for (Space space : spacesFromDao) {
            if (!Objects.equals(space.getFloorId(), floorId)) continue;
            AdminWorkspaceElement element = new AdminWorkspaceElement(this, space);
            workspaces_container.getChildren().add(element.getWorkspaceBox());
        }
    }

    /*
    @FXML
    public void onEditWorkspaceButtonClick(Space space) {
        AdminEditWorkspaceViewController controller = (AdminEditWorkspaceViewController) Main.sceneController.showView("space-edit-workspace.fxml");
        controller.spaceID = space.getId();
        controller.updateView();
    }
     */


    @Override
    public void updateView() {
        showSpacesOnView();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());

    }


    @FXML
    void onApplyClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-space-view-v2.fxml");
        controller.updateView();

    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-space-view-v2.fxml");
        controller.updateView();

    }

}