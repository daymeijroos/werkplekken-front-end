package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.elements.ReservationElement;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ReservationViewController implements ViewController{
    ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());
    SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    @FXML
    public VBox reservation_container;

    private void showReservations(String userId) {
        ArrayList<Reservation> reservationList = reservationDao.getAllByUser(userId);
        for (Reservation reservation : reservationList){
            ReservationElement element = new ReservationElement(this, reservation);
            reservation_container.getChildren().add(element.getReservationContainer());
        }
    }

    @Override
    public void updateView() {
        reservation_container.getChildren().clear();
        showReservations(Main.currentUser.getId());
    }

    public void depricatedOnAddReservation() {
        Reservation reservation = new Reservation("id", Main.currentUser.getId(), "2022-11-29 11:30:00", "2022-11-29 17:30:00", 1, spaceDao.getAll().get(0).getId().toString(), "OPEN");
        reservationDao.post(reservation);
        updateView();
    }

    public void onAddReservation() {
        ReservationEditViewController controller = (ReservationEditViewController) Main.sceneController.showView("reservation-edit-view.fxml");
        controller.updateView();
    }

    public void onCancelReservation(Reservation reservation){
        Reservation deletedReservation = reservation;
        reservationDao.delete(deletedReservation);
        ReservationViewController controller = (ReservationViewController) Main.sceneController.showView("reservation-view.fxml");
        controller.updateView();
    }

    public void onEditButtonClick(Reservation reservation){
        ReservationEditViewController controller = (ReservationEditViewController) Main.sceneController.showView("reservation-edit-view.fxml");
        controller.reservationID = reservation.getId();
        controller.updateView();

    }
}
