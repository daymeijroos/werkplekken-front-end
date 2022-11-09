package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.*;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.*;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class ReservationViewController implements ViewController {
    @FXML
    public VBox main_container;
    public Label buildingName;
    public Label floorDesignation;
    public Label startTime;
    public Label endTime;

    private Reservation currentReservationFromUser;

    private void displayReservations() {
        SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
        ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());
        System.out.println(reservationDao.getAll());
        currentReservationFromUser = reservationDao.getByUser(UUID.fromString(Main.currentUser.getId()));

        System.out.println("this is current mf reservation " + currentReservationFromUser);
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
