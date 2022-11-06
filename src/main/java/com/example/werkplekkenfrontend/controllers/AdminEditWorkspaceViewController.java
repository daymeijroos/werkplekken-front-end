package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
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

    @FXML
    private TextArea name;


    public UUID spaceID = null;

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    private void updateWorkspaceDetails(Space space){
        capacity.setText(String.valueOf(space.getCapacity()));
    }
    @FXML
    void onApplyClick(ActionEvent event) {
        if(spaceID != null){
            Space updatedSpace = new Space(Integer.valueOf(capacity.getText()));
            System.out.println("Patch request response: " + spaceDao.patch(updatedSpace));
        }
        else{
            if (!uniqueCheckFromDao()) return;
            Space newSpace = new Space(Integer.valueOf(capacity.getText())); // there is a chance this generates a duplicate UUID
            System.out.println("Post request response: " + spaceDao.post(newSpace));

        }
        ViewController controller = Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.updateView();

    }

    private boolean uniqueCheckFromDao(){
        List<Space> spaceList = spaceDao.getAll();
        for(Space space : spaceList){
            if (space.getId() == spaceID) continue;
            if (Objects.equals(space.getCapacity(), capacity.getText())) return false;
            if (Objects.equals(space.getCapacity(), capacity.getText())) return false;
        }
        return true;
    }
    private boolean validityCheck(){
        if (capacity.getText() == null) return false;
        return true;
    }


    @FXML
    void onCancelClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.updateView();
    }

    @Override
    public void updateView() {
        //Space spaceFromDao = DaoReplicator.getWorkSpaceFromID(UUID.randomUUID());
        //updateWorkspaceDetails(spaceFromDao);
        if(spaceID != null)  {
            Space spaceFromDao = spaceDao.get(spaceID);
            updateWorkspaceDetails(spaceFromDao);
        }
    }
}
