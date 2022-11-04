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
        Label name = new Label(space.getName());
        Button edit = new Button("edit");
        edit.setOnAction(actionEvent -> parent.onEditWorkspaceButtonClick(this.space));
        workspaceBox = new HBox(name, edit);
    }

    public HBox getWorkspaceBox() {
        return workspaceBox;
    }
}
