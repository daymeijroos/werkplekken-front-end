package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.controllers.ViewController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class NavBarElement {
    String adminPageFXML = "admin-view.fxml";
    String accountPageFXML = "account-view.fxml";
    String reservationPageFXML = "reservation-view.fxml";

    HBox buildingBox;

    public void onAdminButtonClick(){
        ViewController controller = Main.sceneController.showView(adminPageFXML);
        controller.updateView();
    }

    public void onAccountButtonClick(){
        ViewController controller = Main.sceneController.showView(accountPageFXML);
        controller.updateView();
    }

    public void onReservationButtonClick(){
        ViewController controller = Main.sceneController.showView(reservationPageFXML);
        controller.updateView();
    }

    public NavBarElement(){
        Button adminButton = new Button("admin");
        Button accountButton = new Button("account");
        Button reservationButton = new Button("reservation");
        adminButton.setOnAction(actionEvent -> onAdminButtonClick());
        accountButton.setOnAction(actionEvent -> onAccountButtonClick());
        // reservationButton.setOnAction(actionEvent -> onReservationButtonClick());
        buildingBox = new HBox(accountButton, reservationButton, adminButton);
        buildingBox.setAlignment(Pos.CENTER);
    }

    public HBox getBuildingBox() {
        return buildingBox;
    }
}
