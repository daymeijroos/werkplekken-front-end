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
        Button edit = new Button("edit");
        edit.setOnAction(actionEvent -> parent.onEditMeetingRoomButtonClick(this.space));
        meetingroomBox = new HBox(name,edit);
    }


    public HBox getMeetingroomBox() {
        return meetingroomBox;
    }
}
