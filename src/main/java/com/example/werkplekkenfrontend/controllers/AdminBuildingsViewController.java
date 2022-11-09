package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.elements.AdminBuildingElement;
import com.example.werkplekkenfrontend.elements.MessageElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminBuildingsViewController implements ViewController{
    private BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());

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
        ViewController controller = Main.sceneController.showView("admin-edit-building-view.fxml");
        controller.updateView();
        //messagePopup.show(Main.publicStage);
    }

    public void onEditBuildingClick(Building building) {
        AdminEditBuildingViewController controller = (AdminEditBuildingViewController) Main.sceneController.showView("admin-edit-building-view.fxml");
        controller.buildingID = building.getId();
        controller.updateView();
    }

    public void onSelectBuildingClick(Building building) {

    }

    private void showBuildingsOnView(){
        List<Building> buildingsFromDao = buildingDao.getAll();
        for(Building building : buildingsFromDao){
            AdminBuildingElement element = new AdminBuildingElement(this, building);
            buildings_container.getChildren().add(element.getBuildingBox());
        }
    }

    @Override
    public void updateView() {
        showBuildingsOnView();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
        messagePopup = new MessageElement("Too many Buildings added already", "ok").getPopup();
    }
}
