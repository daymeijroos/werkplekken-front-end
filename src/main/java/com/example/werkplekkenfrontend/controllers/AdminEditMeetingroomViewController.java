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

public class AdminEditMeetingroomViewController implements ViewController{

    private SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
    public UUID spaceID = null;


    @FXML
    public TextArea facility;

    @FXML
    public TextArea floor;
    @FXML
    public  TextArea capacity;

    @FXML
    public  TextArea name;


    private void updateMeetingroomDetails(Space space){
        capacity.setText(String.valueOf(space.getCapacity()));
        //floor.setText(space.getFloorId());
    }


    @FXML
    void onApplyClick(ActionEvent event) {

        if(spaceID != null){
            Space updatedSpace = new Space(Integer.valueOf(capacity.getText()));
            System.out.println("Patch request response: " + spaceDao.patch(updatedSpace));
        }
        else{
            if (!uniqueCheckFromDao()) return;
            Space newSpace = new Space(Integer.valueOf(capacity.getText())); // the
            System.out.println("Post request response: " + spaceDao.post(newSpace));

        }
        ViewController controller = Main.sceneController.showView("admin-meetingroom-view.fxml");
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
        if (name.getText() == null || capacity.getText() == null) return false;
        return true;
    }
    @FXML
    void onCancelClick(ActionEvent event) {
        ViewController controller = Main.sceneController.showView("admin-meetingroom-view.fxml");
        controller.updateView();

    }

    @Override
    public void updateView() {
        if(spaceID != null)  {
            Space spaceFromDao = spaceDao.get(spaceID);
            updateMeetingroomDetails(spaceFromDao);
        }
    }

}
