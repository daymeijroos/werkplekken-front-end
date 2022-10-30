package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.models.Building;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class AdminEditBuildingViewController implements ViewController{

    TextArea name = new TextArea();
    TextArea zipcode = new TextArea();
    TextArea city = new TextArea();
    TextArea adress = new TextArea();
    HBox row_2_container = new HBox(zipcode, city);
    @FXML
    public VBox building_container = new VBox(name, row_2_container, adress);

    private void showBuildingDetails(Building building){
        name.setText(building.getName());
        zipcode.setText(building.getZipcode());
        city.setText(building.getCity());
        adress.setText(building.getAdress());

        building_container.getChildren().add(name);
        building_container.getChildren().add(row_2_container);
        building_container.getChildren().add(adress);
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
