package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.elements.AdminMeetingroomElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminMeetingroomViewController implements ViewController{

    @FXML
    public VBox main_container;
    @FXML
    public VBox meetingrooms_container;


    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    @FXML
    void OnNewMeetingroomButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-new-meetingroom.fxml");
        controller.updateView();
    }



    private void showSpacesOnView(){
        List<Space> spacesFromDao = spaceDao.getAll();
        for(Space space : spacesFromDao){
            AdminMeetingroomElement element = new AdminMeetingroomElement(this, space);
            meetingrooms_container.getChildren().add(element.getMeetingroomBox());
        }
    }
    @FXML
    public void onEditMeetingRoomButtonClick(Space space) {
        AdminEditMeetingroomViewController controller = (AdminEditMeetingroomViewController)Main.sceneController.showView("space-edit-meetingroom.fxml");
        controller.spaceID = space.getId();
        controller.updateView();

    }


    @Override
    public void updateView() {
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
        showSpacesOnView();

    }

    public void onEditWorkspaceButtonClick(Space space) {
        AdminEditMeetingroomViewController controller = (AdminEditMeetingroomViewController) Main.sceneController.showView("space-edit-meetingroom.fxml");
        controller.spaceID = space.getId();
        controller.updateView();
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
