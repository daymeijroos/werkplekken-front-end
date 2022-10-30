package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.models.AdminBuildingElement;
import com.example.werkplekkenfrontend.models.Building;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminBuildingsViewController implements ViewController{
    @FXML
    public Label exampleText;
    @FXML
    public VBox buildings_container;

    @FXML
    public void onReturnClick() {
        exampleText.setText("Return is clicked");
    }

    @FXML
    public void onAddBuildingClick() {
        exampleText.setText("Add Building is clicked");
    }

    @FXML
    public void onEditBuildingClick(Building building) {
        exampleText.setText("Edit Building " + building.getName() + " is clicked");
    }

    public void setText(String text) {
        exampleText.setText(text);
    }

    private void showBuildingsOnView(List<Building> buildings){
        for(Building building : buildings){
            AdminBuildingElement element = new AdminBuildingElement(building);
            buildings_container.getChildren().add(element.getBuildingBox());
        }
    }

    List<Building> testBuildings = new ArrayList<>();
    private void setupTestData(){
        Building leiden = new Building(UUID.randomUUID(), "Cool Kids Club", "IL2016", "Leiden", "naast het station");
        Building denHaag = new Building(UUID.randomUUID(), "Get Fucked Club", "HELL00", "Den Haag", "somewhere over the rainbow");
        testBuildings.add(leiden);
        testBuildings.add(denHaag);
    }

    private void openEditView(){

    }

    @Override
    public void updateView() {
        setupTestData();
        showBuildingsOnView(testBuildings);
    }
}
