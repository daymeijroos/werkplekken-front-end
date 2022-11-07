package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.controllers.AdminWorkspaceViewController;
import com.example.werkplekkenfrontend.models.Space;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AdminWorkspaceElement {

    AdminWorkspaceViewController parent;

    Space space;
    HBox workspaceBox;

    public AdminWorkspaceElement(AdminWorkspaceViewController parent, Space space) {
        this.parent = parent;
        this.space = space;
        Label name = new Label(Integer.toString(space.getCapacity()));
        name.setPrefHeight(50);
        name.setPrefWidth(650);
        Button edit = new Button("edit");
        edit.setPrefWidth(50);
        edit.setPrefHeight(50);
        /*
        edit.setOnAction(actionEvent -> parent.onEditWorkspaceButtonClick(this.space));
         */
        workspaceBox = new HBox(name, edit);
        workspaceBox.setMaxWidth(800);
        workspaceBox.setStyle("-fx-padding: 50; -fx-border-color: black");
    }

    public HBox getWorkspaceBox() {
        return workspaceBox;
    }
}
