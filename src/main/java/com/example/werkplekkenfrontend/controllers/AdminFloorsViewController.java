package com.example.werkplekkenfrontend.controllers;

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
import java.util.UUID;

public class AdminFloorsViewController implements ViewController{
    FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    public UUID buildingID = null;

    @FXML
    public VBox floors_container;

    @FXML
    public VBox main_container;

    private void displayFloors() {
        List<Floor> floorsFromDao = floorDao.getAll();
        for (Floor floor : floorsFromDao){
            if (!Objects.equals(floor.getBuildingId(), buildingID.toString())) continue;
            AdminFloorElement element = new AdminFloorElement(this, floor);

        }
    }

    public void onAddFloorClick() {

    }

    public void onEditFloorClick(Floor floor) {
    }

    public void onSelectFloorClick(Floor floor) {
    }

    @Override
    public void updateView() {
        displayFloors();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
