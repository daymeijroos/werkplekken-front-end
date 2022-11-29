package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Objects;
import java.util.UUID;

public class AdminEditWorkspaceViewController implements ViewController{

    @FXML
    private TextArea capacity;

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    public String spaceID = null;

    public String floorId = null;

    private AdminWorkspaceViewController controller;


    private void updateWorkspaceDetails(Space space){
        capacity.setText(String.valueOf(space.getCapacity()));
        floorId = space.getFloorId();
    }
    @FXML
    public void onApplyClick() {
        if (!validityCheck()) return;
        if(spaceID != null){
            Space updatedSpace = null;
            try {
                updatedSpace = new Space(spaceID,Integer.parseInt(capacity.getText()), spaceDao.get(String.valueOf(UUID.fromString(spaceID))).getFloorId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                spaceDao.patch(updatedSpace);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        else{
            Space newSpace = new Space(UUID.randomUUID().toString(),Integer.parseInt(capacity.getText()),floorId); // there is a chance this generates a duplicate UUID
            try {
                spaceDao.post(newSpace);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }

        controller = (AdminWorkspaceViewController)Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.floorId = floorId;
        controller.updateView();

    }
    private boolean validityCheck(){
        return !Objects.equals(capacity.getText(), "");
    }



    public void onCancelClick() {
        controller = (AdminWorkspaceViewController)Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.floorId = floorId;
        controller.updateView();

    }

    @Override
    public void updateView() {
        if(spaceID != null)  {
            try {
                Space spaceFromDao = spaceDao.get(String.valueOf(UUID.fromString(spaceID)));
                updateWorkspaceDetails(spaceFromDao);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
    }
}