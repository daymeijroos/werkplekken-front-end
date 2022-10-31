package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.models.Building;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class AdminEditBuildingViewController implements ViewController{

    @FXML
    public TextArea name;
    @FXML
    public TextArea zipcode;
    @FXML
    public TextArea city;
    @FXML
    public TextArea adress;

    private void showBuildingDetails(Building building){
        name.setText(building.getName());
        zipcode.setText(building.getZipcode());
        city.setText(building.getCity());
        adress.setText(building.getAdress());
    }

    public void onCancelClick(){
        // open confirmation window, if don't cancel is selected return

        // go to adminBuildingsViewController
    }

    public void onApplyClick(){
        if (!validityCheck()) return;

        // open confirmation window, if cancel is selected return

        updateDatabase();

        // go to AdminBuildingsViewController
    }

    private boolean validityCheck(){
        // check if name and adress are unique

        if (name.getText() == null || zipcode.getText() == null || city.getText() == null || adress.getText() == null){
            return false;
        }

        return true;
    }

    private void updateDatabase(){

    }

    private Building generateTestBuilding(){
        return new Building(UUID.randomUUID(), "Cool Kids Club", "IL2016", "Leiden", "naast het station");
    }

    @Override
    public void updateView() {
        Building testBuilding = generateTestBuilding();
        showBuildingDetails(testBuilding);
    }
}
