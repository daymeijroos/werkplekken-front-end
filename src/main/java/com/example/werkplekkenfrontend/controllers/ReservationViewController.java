package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.daos.*;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.*;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Objects;

public class ReservationViewController implements ViewController {
    @FXML
    public VBox main_container;
    public Label buildingName;
    public Label floorDesignation;
    public Label startTime;
    public Label endTime;

    private void displayReservations() {
        ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());
        ArrayList<Reservation> reservations = reservationDao.getAll();

        UserDao userDao = new UserDao(new HttpService(), new ObjectMapper());
        User user = userDao.getCurrent();

        SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
        ArrayList<Space> spaces = spaceDao.getAll();

        FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
        ArrayList<Floor> floors = floorDao.getAll();

        BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());
        ArrayList<Building> buildings = buildingDao.getAll();

        for (Reservation reservation : reservations) {
            if (Objects.equals(reservation.getUserId(), user.getId())) {
                for (Space space: spaces) {
                    if (Objects.equals(reservation.spaceId, space.getId())) {
                        for (Floor floor: floors) {
                            for (Building building: buildings) {
                                if (Objects.equals(floor.getBuildingId(), building.getId())) {
                                    buildingName.setText(building.getAddress());
                                    floorDesignation.setText(floor.getDesignation());
                                    startTime.setText(reservation.dateIn);
                                    endTime.setText(reservation.dateOut);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void onClickCancel(ActionEvent actionEvent) {
        // todo
    }

    public void onClickCheckIn(ActionEvent actionEvent) {
        // todo
    }

    public void onClickCreateReservation(ActionEvent actionEvent) {
        // todo
    }

    @Override
    public void updateView() {
        displayReservations();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
    }
}
