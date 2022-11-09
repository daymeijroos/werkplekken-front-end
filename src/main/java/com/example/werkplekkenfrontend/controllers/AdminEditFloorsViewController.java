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

public class AdminEditFloorsViewController implements ViewController{
    private FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    public String floorId = null;
    public String buildingId = null;
    private AdminFloorsViewController controller;

    @FXML
    public TextArea designation;

    private void updateFloorDetails(Floor floor){
        designation.setText(floor.getDesignation());
        buildingId = floor.getBuildingId();
    }

    public void onCancelClick(){
        AdminFloorsViewController controller = (AdminFloorsViewController) Main.sceneController.showView("admin-floor-view.fxml");
        controller.buildingId = floorDao.get(UUID.fromString(floorId)).getBuildingId();
        controller.updateView();
    }

    public void onApplyClick(){
        if (!validityCheck()) return;
        if (floorId != null) {
            Floor updatedFloor = new Floor(floorId, designation.getText(), floorDao.get(UUID.fromString(floorId)).getBuildingId());
            floorDao.patch(updatedFloor);
        }
        else {
            Floor newFloor = new Floor(UUID.randomUUID().toString(), designation.getText(), buildingId);
            floorDao.post(newFloor);
        }
        controller = (AdminFloorsViewController) Main.sceneController.showView("admin-floor-view.fxml");
        controller.buildingId = buildingId;
        controller.updateView();
    }

    private boolean validityCheck(){
        return !Objects.equals(designation.getText(), "");
    }

    @Override
    public void updateView() {
        if (floorId != null) {
            Floor floorFromDao = floorDao.get(UUID.fromString(floorId));
            updateFloorDetails(floorFromDao);
        }
    }
}
