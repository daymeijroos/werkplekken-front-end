package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.elements.AdminBuildingElement;
import com.example.werkplekkenfrontend.elements.MessageElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Building;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminBuildingsViewController implements ViewController{
    @FXML
    public VBox buildings_container;
    @FXML
    public VBox main_container;

    Popup messagePopup;

    @FXML
    public void onReturnClick() {
        ViewController controller = Main.sceneController.showView("admin-view.fxml");
        controller.updateView();
    }

    @FXML
    public void onAddBuildingClick() {
        //ViewController controller = Main.sceneController.showView("admin-edit-building-view.fxml");
        //controller.updateView();
        messagePopup.show(Main.publicStage);
    }

    public void onEditBuildingClick(Building building) {
        AdminEditBuildingViewController controller = (AdminEditBuildingViewController) Main.sceneController.showView("admin-edit-building-view.fxml");
        controller.buildingID = building.getId();
        controller.updateView();
    }

    private void showBuildingsOnView(List<Building> buildings){
        for(Building building : buildings){
            AdminBuildingElement element = new AdminBuildingElement(this, building);
            buildings_container.getChildren().add(element.getBuildingBox());
        }
    }

    private List<Building> setupTestData(){
        List<Building> testBuildings = new ArrayList<>();
        Building leiden = new Building(UUID.randomUUID(), "Cool Kids Club", "IL2016", "Leiden", "naast het station");
        Building denHaag = new Building(UUID.randomUUID(), "Get Fucked Club", "HELL00", "Den Haag", "somewhere over the rainbow");
        testBuildings.add(leiden);
        testBuildings.add(denHaag);
        return testBuildings;
    }

    @Override
    public void updateView() {
        List<Building> buildingsFromDao = setupTestData();
        showBuildingsOnView(buildingsFromDao);
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
        messagePopup = new MessageElement("Too many Buildings added already", "ok").getPopup();
    }
}