package com.example.werkplekkenfrontend.elements;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class MessageElement {
    VBox container;
    Popup popup = new Popup();

    private void onButtonClick(){
        popup.hide();
    }

    public MessageElement(String message, String buttonMessage){
        Label label = new Label(message);
        Button button = new Button(buttonMessage);
        button.setMinHeight(50);
        button.setMinWidth(200);
        button.setOnAction(actionEvent -> onButtonClick());
        container = new VBox(label, button);
        popup.getContent().add(container);
    }

    public Popup getPopup() {
        return popup;
    }
}
