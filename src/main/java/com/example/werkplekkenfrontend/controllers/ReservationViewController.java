package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.elements.ReservationElement;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ReservationViewController implements ViewController{
    ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());

    @FXML
    public VBox reservation_container;

    private void showReservations(String userId) {
        try {
            ArrayList<Reservation> reservationList = reservationDao.getAllByUser(userId);
            for (Reservation reservation : reservationList) {
                ReservationElement element = new ReservationElement(this, reservation);
                reservation_container.getChildren().add(element.getReservationContainer());
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

    public void onAddReservation() {
        ReservationEditViewController controller = (ReservationEditViewController) Main.sceneController.showView("reservation-edit-view.fxml");
        controller.updateView();
    }
}