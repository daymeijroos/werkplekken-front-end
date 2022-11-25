package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.elements.NavBarElement;
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
        try {
            List<Reservation> reservationList = reservationDao.getAllByUser(userId);
            for (Reservation reservation : reservationList){
                ReservationElement element = new ReservationElement(this, reservation);
                reservation_container.getChildren().add(element.getBuildingBox());
            }
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
        }
    }

    @Override
    public void updateView() {
        reservation_container.getChildren().clear();
        showReservations(Main.currentUser.getId());
        reservation_container.getChildren().add(new NavBarElement().getBuildingBox());
    }

    public void onAddReservation(ActionEvent actionEvent) {
        try {
            Reservation reservation = new Reservation("id", Main.currentUser.getId(), 999999989L, 9999999999L, 1, spaceDao.getAll().get(0).getId().toString(), "OPEN");
            reservationDao.post(reservation);
        } catch (Exception e) {
            Main.sceneController.showError("Oops");
        }
        updateView();
    }
}
