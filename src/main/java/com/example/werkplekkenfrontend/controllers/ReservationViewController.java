package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.elements.ReservationElement;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ReservationViewController implements ViewController{
    public VBox main_container;
    ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());

    @FXML
    public VBox reservation_container;

    private void showReservations(String userId) {
        try {
            ArrayList<Reservation> reservationList = reservationDao.getAllByUser(userId);
            for (Reservation reservation : reservationList){
                ReservationElement element = new ReservationElement(this, reservation);
                reservation_container.getChildren().add(element.getReservationContainer());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateView() {
        reservation_container.getChildren().clear();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
        showReservations(Main.currentUser.getId());
    }

    public void onAddReservation() {
        ReservationEditViewController controller = (ReservationEditViewController) Main.sceneController.showView("reservation-edit-view.fxml");
        controller.updateView();
    }

    public void onIncheckReservation(Reservation reservation) {
        try {
            Reservation incheckReservation = new Reservation(reservation.id,reservation.userId,reservation.dateIn,reservation.dateOut,reservation.amountOfPeople,reservation.spaceId,"FULFILLED");
            reservationDao.patch(incheckReservation);
            ReservationViewController controller = (ReservationViewController) Main.sceneController.showView("reservation-view.fxml");
            controller.updateView();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void onCancelReservation(Reservation reservation){
        reservationDao.delete(reservation);
        ReservationViewController controller = (ReservationViewController) Main.sceneController.showView("reservation-view.fxml");
        controller.updateView();
    }

    public void onEditButtonClick(Reservation reservation){
        ReservationEditViewController controller = (ReservationEditViewController) Main.sceneController.showView("reservation-edit-view.fxml");
        controller.reservationID = reservation.getId();
        controller.updateView();

    }
}