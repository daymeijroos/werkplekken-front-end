package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.controllers.AdminBuildingsViewController;
import com.example.werkplekkenfrontend.models.Building;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AdminBuildingElement {
    AdminBuildingsViewController parent;
    Building building;
    HBox buildingBox;

    public AdminBuildingElement(AdminBuildingsViewController parent, Building building) {
        this.parent = parent;
        this.building = building;
        Label name = new Label(building.getName());
        name.setPrefHeight(50);
        name.setPrefWidth(650);
        Button edit = new Button("edit");
        edit.setPrefWidth(50);
        edit.setPrefHeight(50);
        edit.setOnAction(actionEvent -> parent.onEditBuildingClick(this.building));
        buildingBox = new HBox(name, edit);
        buildingBox.setMaxWidth(800);
        buildingBox.setStyle("-fx-padding: 50; -fx-border-color: black");
    }

    public HBox getBuildingBox() {
        return buildingBox;
    }
}
