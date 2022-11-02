package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.models.DaoReplicator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AdminEditBuildingViewController implements ViewController{
    public UUID buildingID = null;

    @FXML
    public TextArea name;
    @FXML
    public TextArea zipcode;
    @FXML
    public TextArea city;
    @FXML
    public TextArea adress;

    // show details from building that is being edited
    private void updateBuildingDetails(Building building){
        name.setText(building.getName());
        zipcode.setText(building.getZipcode());
        city.setText(building.getCity());
        adress.setText(building.getAdress());
    }

    // returns to admin buildings view without updating database
    public void onCancelClick(){
        // open confirmation window, if don't cancel is selected return

        buildingID = null; // not sure if this is necessary
        ViewController controller = Main.sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
    }

    public void onApplyClick(){
        if (!validityCheck()) return;

        // open confirmation window, if cancel is selected return

        if (buildingID == null){
            Building newBuilding = new Building(UUID.randomUUID(), name.getText(), zipcode.getText(), city.getText(), adress.getText()); // there is a chance this generates a duplicate UUID
            DaoReplicator.POST_Building(newBuilding);
        }
        else {
            Building updatedBuilding = new Building(buildingID, name.getText(), zipcode.getText(), city.getText(), adress.getText());
            DaoReplicator.PATCH_Building(updatedBuilding);
        }
        buildingID = null; // not sure if this is necessary
        ViewController controller = Main.sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
    }

    // check if values are valid
    private boolean validityCheck(){
        if (!uniqueCheckFromDao()) return false;
        if (name.getText() == null || zipcode.getText() == null || city.getText() == null || adress.getText() == null) return false;
        return true;
    }

    // check if name and address are not already in use.
    private boolean uniqueCheckFromDao(){
        List<Building> buildingList = DaoReplicator.getBuildings();
        for(Building building : buildingList){
            if (building.getId() == buildingID) continue;
            if (Objects.equals(building.getName(), name.getText()) || Objects.equals(building.getAdress(), adress.getText())) return false;
        }
        return true;
    }

    @Override
    public void updateView() {
        if(buildingID != null) {
            Building buildingFromDao = DaoReplicator.getBuildingFromID(UUID.randomUUID());
            updateBuildingDetails(buildingFromDao);
        }
    }
}