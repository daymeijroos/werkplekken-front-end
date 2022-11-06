package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.LoginDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.elements.AdminWorkspaceElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Facility;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class AdminWorkspaceViewController implements ViewController{

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
    @FXML
    public VBox workspaces_container;

    @FXML
    public VBox main_container;

    @FXML
    void OnNewWorkspaceButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-new-workspace.fxml");
        controller.updateView();
    }

    private void showSpacesOnView(){
        List<Space> spacesFromDao = spaceDao.getAll();
        for(Space space : spacesFromDao){
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
        showSpacesOnView();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());

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
