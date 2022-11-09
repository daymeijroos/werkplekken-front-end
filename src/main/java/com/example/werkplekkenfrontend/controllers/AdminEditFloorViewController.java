package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.FloorDao;
import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Objects;
import java.util.UUID;

public class AdminEditFloorViewController implements ViewController{
    private FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    public UUID floorID = null;
    public UUID buildingID = null;
    private AdminFloorsViewController controller;

    @FXML
    public TextArea designation;

    private void updateFloorDetails(Floor floor){
        designation.setText(floor.getDesignation());
        buildingID = UUID.fromString(floor.getBuildingId());
    }

    public void onCancelClick(){
        AdminFloorsViewController controller = (AdminFloorsViewController) Main.sceneController.showView("admin-floors-view.fxml");
        controller.buildingID = UUID.fromString(floorDao.get(floorID).getBuildingId());
        controller.updateView();
    }

    public void onApplyClick(){
        if (!validityCheck()) return;
        if (floorID != null) {
            Floor updatedFloor = new Floor(floorID.toString(), designation.getText(), floorDao.get(floorID).getBuildingId());
            floorDao.patch(updatedFloor);
        }
        else {
            Floor newFloor = new Floor(UUID.randomUUID().toString(), designation.getText(), buildingID.toString());
            floorDao.post(newFloor);
        }
        controller = (AdminFloorsViewController) Main.sceneController.showView("admin-floors-view.fxml");
        controller.updateView();
    }

    private boolean validityCheck(){
        return !Objects.equals(designation.getText(), "");
    }

    @Override
    public void updateView() {
        if (floorID != null) {
            Floor floorFromDao = floorDao.get(floorID);
            updateFloorDetails(floorFromDao);
        }
    }
}
