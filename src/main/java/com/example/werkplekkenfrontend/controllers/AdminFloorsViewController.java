package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.FloorDao;
import com.example.werkplekkenfrontend.elements.AdminFloorElement;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

public class AdminFloorsViewController implements ViewController{
    FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    public String buildingId = null;

    @FXML
    public VBox floors_container;

    @FXML
    public VBox main_container;

    private void displayFloors() {
        List<Floor> floorsFromDao = floorDao.getAll();
        for (Floor floor : floorsFromDao){
            if (!Objects.equals(floor.getBuildingId(), buildingId)) continue;
            AdminFloorElement element = new AdminFloorElement(this, floor);
            floors_container.getChildren().add(element.getBuildingBox());
        }
    }

    public void onAddFloorClick() {
        AdminEditFloorsViewController controller = (AdminEditFloorsViewController) Main.sceneController.showView("admin-edit-floor-view.fxml");
        controller.buildingId = buildingId;
        controller.updateView();
    }

    public void onEditFloorClick(Floor floor) {
        AdminEditFloorsViewController controller = (AdminEditFloorsViewController) Main.sceneController.showView("admin-edit-floor-view.fxml");
        controller.floorId = floor.getId();
        controller.updateView();
    }

    public void onSelectFloorClick(Floor floor) {
        AdminWorkspaceViewController controller = (AdminWorkspaceViewController) Main.sceneController.showView("admin-workspace-meetingroom-view.fxml");
        controller.floorId = floor.getId();
        controller.updateView();
    }

    public void onReturnClick() {
        ViewController controller = Main.sceneController.showView("admin-buildings-view.fxml");
        controller.updateView();
    }

    @Override
    public void updateView() {
        displayFloors();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
