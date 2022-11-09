package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.controllers.AdminBuildingsViewController;
import com.example.werkplekkenfrontend.controllers.AdminFloorsViewController;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.models.Floor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AdminFloorElement {
    AdminFloorsViewController parent;
    Floor floor;
    HBox buildingBox;

    public AdminFloorElement(AdminFloorsViewController parent, Floor floor) {
        this.parent = parent;
        this.floor = floor;
        Label name = new Label(floor.getDesignation());
        name.setPrefHeight(80);
        name.setPrefWidth(650);
        Button edit = new Button("edit");
        edit.setMinWidth(80);
        edit.setMinHeight(80);
        edit.setOnAction(actionEvent -> parent.onEditFloorClick(this.floor));
        Button select = new Button("select");
        select.setMinWidth(80);
        select.setMinHeight(80);
        select.setOnAction(actionEvent -> parent.onSelectFloorClick(this.floor));
        buildingBox = new HBox(name, edit, select);
        buildingBox.setMaxWidth(800);
        buildingBox.setStyle("-fx-padding: 50; -fx-border-color: black");
    }

    public HBox getBuildingBox() {
        return buildingBox;
    }
}
