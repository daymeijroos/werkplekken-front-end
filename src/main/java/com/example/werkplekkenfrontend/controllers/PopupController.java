package com.example.werkplekkenfrontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PopupController {
    private SceneController parent;

    @FXML
    Label message;

    @FXML
    Button button;

    public void setMessage(String messageString) {
        this.message.setText(messageString);
        this.button.setText("Okay");
    }

    public void setParent(SceneController parent) {
        this.parent = parent;
    }

    @FXML
    public void closeError() {
        parent.closeError();
    }

}
