package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.elements.AdminMeetingroomElement;
import com.example.werkplekkenfrontend.models.Space;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminMeetingroomViewController implements ViewController{

    @FXML
    public VBox meetingrooms_container;

    @FXML
    void OnNewMeetingroomButtonClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("space-view-new-meetingroom.fxml");
        controller.updateView();
    }



    private void showSpacesOnView(List<Space> spaces){
        for(Space space : spaces){
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

    private List<Space> setupTestData(){
        List<Space> testSpaces = new ArrayList<>();
        Space vergaderruimte_1 = new Space(UUID.randomUUID(),10,"sponge", "vergaderingruimte_1");
        testSpaces.add(vergaderruimte_1);
        return testSpaces;
    }



    @Override
    public void updateView() {
        List<Space> spacesFromDao = setupTestData();
        showSpacesOnView(spacesFromDao);

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
