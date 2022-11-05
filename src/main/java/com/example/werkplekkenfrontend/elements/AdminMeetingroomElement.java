package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.controllers.AdminMeetingroomViewController;
import com.example.werkplekkenfrontend.models.Space;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AdminMeetingroomElement {

    AdminMeetingroomViewController parent;

    Space space;
    HBox meetingroomBox;

    public AdminMeetingroomElement(AdminMeetingroomViewController parent, Space space) {
        this.parent = parent;
        this.space = space;
        Label name = new Label(space.getName());
        name.setPrefHeight(50);
        name.setPrefWidth(650);
        Button edit = new Button("edit");
        edit.setPrefWidth(50);
        edit.setPrefHeight(50);
        edit.setOnAction(actionEvent -> parent.onEditMeetingRoomButtonClick(this.space));
        meetingroomBox = new HBox(name,edit);
        meetingroomBox.setMaxWidth(800);
        meetingroomBox.setStyle("-fx-padding: 50; -fx-border-color: black");
    }


    public HBox getMeetingroomBox() {
        return meetingroomBox;
    }
}
