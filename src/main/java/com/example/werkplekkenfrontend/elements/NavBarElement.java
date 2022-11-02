package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.controllers.ViewController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class NavBarElement {
    String adminPageFXML = "admin-view.fxml";
    String userPageFXML = "user-view.fxml";
    String reservationPageFXML = "reservation-view.fxml";

    HBox buildingBox;

    public void onAdminButtonClick(){
        ViewController controller = Main.sceneController.showView(adminPageFXML);
        controller.updateView();
    }

    public void onUserButtonClick(){
        ViewController controller = Main.sceneController.showView(userPageFXML);
        controller.updateView();
    }

    public void onReservationButtonClick(){
        ViewController controller = Main.sceneController.showView(reservationPageFXML);
        controller.updateView();
    }

    public NavBarElement(){
        Button adminButton = new Button("admin");
        Button userButton = new Button("user");
        Button reservationButton = new Button("reservation");
        adminButton.setOnAction(actionEvent -> onAdminButtonClick());
        // userButton.setOnAction(actionEvent -> onUserButtonClick());
        // reservationButton.setOnAction(actionEvent -> onReservationButtonClick());
        buildingBox = new HBox(userButton, reservationButton, adminButton);
    }

    public HBox getBuildingBox() {
        return buildingBox;
    }
}
