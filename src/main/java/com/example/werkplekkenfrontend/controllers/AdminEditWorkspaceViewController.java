package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Objects;
import java.util.UUID;

public class AdminEditWorkspaceViewController implements ViewController{

    @FXML
    private TextArea capacity;

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    @FXML
    private TextArea name;


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
            Space updatedSpace = new Space(spaceID,Integer.valueOf(capacity.getText()), spaceDao.get(UUID.fromString(spaceID)).getFloorId());
            spaceDao.patch(updatedSpace);
        }
        else{
            Space newSpace = new Space(UUID.randomUUID().toString(),Integer.valueOf(capacity.getText()),floorId); // there is a chance this generates a duplicate UUID
            spaceDao.post(newSpace);

        }

        controller = (AdminWorkspaceViewController)Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.floorId = floorId;
        controller.updateView();

    }
    private boolean validityCheck(){
        return !Objects.equals(capacity.getText(), "");
    }



    public void onCancelClick() {
        AdminEditWorkspaceViewController controller = (AdminEditWorkspaceViewController) Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.floorId = spaceDao.get(UUID.fromString(spaceID)).getFloorId();
        controller.updateView();
    }

    @Override
    public void updateView() {
        if(spaceID != null)  {
            Space spaceFromDao = spaceDao.get(UUID.fromString(spaceID));
            updateWorkspaceDetails(spaceFromDao);
        }
    }
}