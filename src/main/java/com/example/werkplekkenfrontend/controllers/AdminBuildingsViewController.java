package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.elements.AdminBuildingElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;

public class AdminBuildingsViewController implements ViewController{
    private final BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());

    @FXML
    public VBox buildings_container;

    @FXML
    public VBox main_container;


    @FXML
    public void onReturnClick() {
        ViewController controller = Main.sceneController.showView("admin-view.fxml");
        controller.updateView();
    }

    @FXML
    public void onAddBuildingClick() {
        ViewController controller = Main.sceneController.showView("admin-edit-building-view.fxml");
        controller.updateView();
    }

    public void onEditBuildingClick(Building building) {
        AdminEditBuildingViewController controller = (AdminEditBuildingViewController) Main.sceneController.showView("admin-edit-building-view.fxml");
        controller.buildingID = building.getId();
        controller.updateView();
    }

    public void onSelectBuildingClick(Building building) {
        AdminFloorsViewController controller = (AdminFloorsViewController) Main.sceneController.showView("admin-floor-view.fxml");
        controller.buildingId = building.getId();
        controller.updateView();
    }

    private void showBuildingsOnView(){
        try {
            List<Building> buildingsFromDao = buildingDao.getAll();
            for(Building building : buildingsFromDao){
                AdminBuildingElement element = new AdminBuildingElement(this, building);
                buildings_container.getChildren().add(element.getBuildingBox());
            }
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
            e.printStackTrace();
        }
    }

    @Override
    public void updateView() {
        showBuildingsOnView();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}