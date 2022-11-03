package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

import java.lang.reflect.Constructor;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MakeReservationViewController implements ViewController {
    ReservationDao reservationDao;
    String FilterBuildingID = null;
    //Workspace pickedSpace = null;

    @FXML
    DatePicker beginTime;

    @FXML
    DatePicker endTime;

    @FXML
    VBox nestBox;

    @Override
    public void updateView() {
        ArrayList<Reservation> reservations = reservationDao.getAll();

        showReservationsOnView(reservations);
    }

    private void showMessageFailed(String message) {

    }

    private void showReservationsOnView(ArrayList<Reservation> reservations) {

    }

    @FXML
    public void showFiltersView() {}

    @FXML
    public void cancelMakingReservation() {}

    @FXML
    public void placeReservation() {
        Reservation reservation = new Reservation();
        //USER
        reservation.dateIn = beginTime.getValue();
        reservation.dateOut = endTime.getValue();
        //reservation.spaceId = pickedSpace
    }
}
