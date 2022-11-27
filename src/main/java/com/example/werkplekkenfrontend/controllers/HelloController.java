package com.example.werkplekkenfrontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController implements ViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setText(String text) {
        welcomeText.setText(text);
    }

    @Override
    public void updateView() {

    }
}