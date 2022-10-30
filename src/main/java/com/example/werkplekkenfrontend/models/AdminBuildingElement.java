package com.example.werkplekkenfrontend.models;

import com.example.werkplekkenfrontend.controllers.AdminBuildingsViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AdminBuildingElement {

    HBox buildingBox;
    Building building;

    public AdminBuildingElement(Building building) {
        this.building = building;
        Label name = new Label(building.getName());
        Button options = new Button("Options");
        buildingBox = new HBox(name, options);
    }

    public HBox getBuildingBox() {
        return buildingBox;
    }

    public Building getBuilding() {
        return building;
    }
}
