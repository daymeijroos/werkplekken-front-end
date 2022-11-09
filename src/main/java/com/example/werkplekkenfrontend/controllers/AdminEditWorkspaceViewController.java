package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.FloorDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AdminEditWorkspaceViewController implements ViewController{

    @FXML
    private TextArea capacity;

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    @FXML
    private TextArea name;


    public UUID spaceID = null;

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
            Space updatedSpace = new Space(spaceID,Integer.valueOf(capacity.getText()), spaceDao.get(spaceID).getFloorId());
            spaceDao.patch(updatedSpace);
        }
        else{
            //if (!uniqueCheckFromDao()) return;
            Space newSpace = new Space(UUID.randomUUID(),Integer.valueOf(capacity.getText()),floorId); // there is a chance this generates a duplicate UUID
            System.out.println("Post request response: " + spaceDao.post(newSpace));

        }

        controller = (AdminWorkspaceViewController)Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.floorId = floorId;
        controller.updateView();

    }
/*
    private boolean uniqueCheckFromDao(){
        List<Space> spaceList = spaceDao.getAll();
        for(Space space : spaceList){
            if (space.getId() == spaceID) continue;
            if (Objects.equals(space.getCapacity(), capacity.getText())) return false;
            if (Objects.equals(space.getCapacity(), capacity.getText())) return false;
        }
        return true;
    }

 */
    private boolean validityCheck(){
        return !Objects.equals(capacity.getText(), "");
    }



    public void onCancelClick() {
        AdminEditWorkspaceViewController controller = (AdminEditWorkspaceViewController) Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.floorId = spaceDao.get(spaceID).getFloorId();
        controller.updateView();
    }

    @Override
    public void updateView() {
        //Space spaceFromDao = DaoReplicator.getWorkSpaceFromID(UUID.randomUUID());
        //updateWorkspaceDetails(spaceFromDao);
        if(spaceID != null)  {
            Space spaceFromDao = spaceDao.get(spaceID);
            //Space spaceFromDao = spaceDao.get(spaceID);
            updateWorkspaceDetails(spaceFromDao);
        }
    }
}