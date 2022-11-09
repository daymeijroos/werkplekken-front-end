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
    public UUID floorId = null;
    public UUID buildingId = null;
    private AdminFloorsViewController controller;

    @FXML
    public TextArea designation;

    private void updateFloorDetails(Floor floor){
        designation.setText(floor.getDesignation());
        buildingId = UUID.fromString(floor.getBuildingId());
    }

    public void onCancelClick(){
        AdminFloorsViewController controller = (AdminFloorsViewController) Main.sceneController.showView("admin-floors-view.fxml");
        controller.buildingId = UUID.fromString(floorDao.get(floorId).getBuildingId());
        controller.updateView();
    }

    public void onApplyClick(){
        if (!validityCheck()) return;
        if (floorId != null) {
            Floor updatedFloor = new Floor(floorId.toString(), designation.getText(), floorDao.get(floorId).getBuildingId());
            floorDao.patch(updatedFloor);
        }
        else {
            Floor newFloor = new Floor(UUID.randomUUID().toString(), designation.getText(), buildingId.toString());
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
        if (floorId != null) {
            Floor floorFromDao = floorDao.get(floorId);
            updateFloorDetails(floorFromDao);
        }
    }
}
