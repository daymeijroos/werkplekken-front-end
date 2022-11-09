package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.elements.ReservationElement;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;

public class ReservationViewController implements ViewController{
    ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());
    SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    @FXML
    public VBox reservation_container;

    private void showReservations(String userId) {
        List<Reservation> reservationList = reservationDao.getAllByUser(userId);
        for (Reservation reservation : reservationList){
            ReservationElement element = new ReservationElement(this, reservation);
            reservation_container.getChildren().add(element.getBuildingBox());
        }
    }

    @Override
    public void updateView() {
        reservation_container.getChildren().clear();
        showReservations(Main.currentUser.getId());
    }

    public void onAddReservation(ActionEvent actionEvent) {
        Reservation reservation = new Reservation("id", Main.currentUser.getId(), 99989L, 999999L, 1, spaceDao.getAll().get(0).getId().toString());
        reservationDao.post(reservation);
        updateView();
    }
}
