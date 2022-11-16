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
        name.setPrefHeight(80);
        name.setPrefWidth(600);
        Button edit = new Button("edit");
        edit.setMinWidth(80);
        edit.setMinHeight(80);
        edit.setOnAction(actionEvent -> parent.onEditBuildingClick(this.building));
        Button select = new Button("select");
        select.setMinWidth(80);
        select.setMinHeight(80);
        select.setOnAction(actionEvent -> parent.onSelectBuildingClick(this.building));
        buildingBox = new HBox(name, edit, select);
        buildingBox.setMaxWidth(800);
        buildingBox.setStyle("-fx-padding: 50; -fx-border-color: black");
    }

    public HBox getBuildingBox() {
        return buildingBox;
    }
}
