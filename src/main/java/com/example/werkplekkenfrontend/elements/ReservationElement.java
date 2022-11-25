package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.controllers.ReservationViewController;
import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.daos.FloorDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.UUID;

public class ReservationElement {
    SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
    FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());
    ReservationViewController parent;
    Reservation reservation;
    HBox buildingBox;

    String floorId;
    String buildingId;

    public ReservationElement (ReservationViewController parent, Reservation reservation){
        try {
            this. parent = parent;
            this.reservation = reservation;
            floorId = spaceDao.get(UUID.fromString(reservation.spaceId)).getFloorId();
            buildingId = floorDao.get(UUID.fromString(floorId)).getBuildingId();
            Label floor = new Label(floorDao.get(UUID.fromString(floorId)).getDesignation());
            floor.setPrefHeight(80);
            floor.setPrefWidth(400);
            Label building = new Label(buildingDao.get(UUID.fromString(buildingId)).getName());
            building.setPrefHeight(80);
            building.setPrefWidth(400);
            buildingBox = new HBox(floor, building);
            buildingBox.setMaxWidth(800);
            buildingBox.setStyle("-fx-padding: 50; -fx-border-color: black");
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
        }
    }

    public HBox getBuildingBox() {
        return buildingBox;
    }
}
