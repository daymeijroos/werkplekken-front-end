package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AdminEditBuildingViewController implements ViewController{
    private ViewController adminBuildingViewController;
    private BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());
    public String buildingID = null;

    @FXML
    public TextArea name;
    @FXML
    public TextArea zipcode;
    @FXML
    public TextArea city;
    @FXML
    public TextArea address;

    // show details from building that is being edited
    private void updateBuildingDetails(Building building){
        name.setText(building.getName());
        zipcode.setText(building.getZipcode());
        city.setText(building.getCity());
        address.setText(building.getAddress());
    }

    // returns to admin buildings view without updating database
    public void onCancelClick(){
        // open confirmation window, if don't cancel is selected return

        buildingID = null; // not sure if this is necessary
        ViewController controller = Main.sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
    }

    public void onApplyClick(){
        List<String> textAreas = List.of(name.getText(), zipcode.getText(), city.getText(), address.getText());
        if (!validityCheck(textAreas)) return;

        // open confirmation window, if cancel is selected return

        if (buildingID != null) {
            try {
                Building updatedBuilding = new Building(buildingID, name.getText(), zipcode.getText(), city.getText(), address.getText());
                buildingDao.patch(updatedBuilding);
            } catch (Exception e) {
                Main.sceneController.showError("Oops");
                e.printStackTrace();
            }
        }
        else {
            try {
                if (!uniqueCheckFromDao()) return;
                Building newBuilding = new Building(UUID.randomUUID().toString(), name.getText(), zipcode.getText(), city.getText(), address.getText()); // there is a chance this generates a duplicate UUID
                buildingDao.post(newBuilding);
            } catch (Exception e) {
                Main.sceneController.showError("Oops");
                e.printStackTrace();
            }
        }
        buildingID = null; // not sure if this is necessary
        adminBuildingViewController = Main.sceneController.showView("admin-buildings-view.fxml");
        adminBuildingViewController.updateView();
    }

    // check if values do not contain empty strings
    public boolean validityCheck(List<String> items){
        for (String item : items) {
            if (item.equals("")) return false;
        }
        return true;
    }

    // check if name and address are not already in use.
    private boolean uniqueCheckFromDao(){
        try {
            List<Building> buildingList = buildingDao.getAll();
            for(Building building : buildingList){
                if (Objects.equals(building.getId(), buildingID)) continue;
                if (Objects.equals(building.getName(), name.getText()) || Objects.equals(building.getAddress(), address.getText())) return false;
            }
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void updateView() {
        try {
            if(buildingID != null) {
                Building buildingFromDao = buildingDao.get(buildingID);
                updateBuildingDetails(buildingFromDao);
            }
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
            e.printStackTrace();
        }
    }
}