package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Objects;

public class AdminEditWorkspaceViewController implements ViewController{

    @FXML
    private TextArea capacity;

    private final SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    @FXML
    private TextArea name;

    private Space space_test;


    public String spaceID = null;

    public String floorId = null;

    private AdminWorkspaceViewController controller;

    private void updateWorkspaceDetails(Space space){
        capacity.setText(String.valueOf(space.getCapacity()));
        floorId = space.getFloorId();
    }

    @FXML
    void onApplyClick() {
        try {
            if (spaceID != null) {
                Space updatedSpace = new Space(spaceID,Integer.parseInt(capacity.getText()),space_test.getFloorId());
                spaceDao.patch(updatedSpace);
            }
            else {
                Space newSpace = new Space(Integer.parseInt(capacity.getText())); // there is a chance this generates a duplicate UUID
                spaceDao.post(newSpace);

            }
            spaceID = null;
            controller = (AdminWorkspaceViewController)Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
            controller.updateView();
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
        }
    }

    private boolean validityCheck(){
        return !Objects.equals(capacity.getText(), "");
    }

    public void onCancelClick() {
        try {
            AdminEditWorkspaceViewController controller = (AdminEditWorkspaceViewController) Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
            controller.floorId = spaceDao.get(spaceID).getFloorId();
            controller.updateView();
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
        }
    }

    @Override
    public void updateView() {
        try {
            if(spaceID != null)  {
                space_test = spaceDao.get(spaceID);
                updateWorkspaceDetails(space_test);
            }
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
        }
    }
}