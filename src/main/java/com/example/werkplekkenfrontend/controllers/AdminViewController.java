package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AdminViewController implements ViewController{
    @FXML
    public VBox main_container;

    public void onBuildingsButtonClick(){
        ViewController controller = Main.sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
    }

    public void onRolesButtonClick(){

    }

    @Override
    public void updateView() {
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
