package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.daos.SpaceDAO;
import com.example.werkplekkenfrontend.daos.UserDao;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ReservationViewController implements ViewController {
    @FXML
    public VBox main_container;
    public Label building;
    public Label floor;
    public Label space_on_floor;
    public Label startTime;
    public Label endTime;

    private void displayReservations() {
        ReservationDao reservationDao = new ReservationDao();
        ArrayList<Reservation> reservations = reservationDao.getAll();

        UserDao userDao = new UserDao();
        User user = userDao.getCurrent();

        SpaceDAO spaceDAO = new SpaceDAO();
        ArrayList<Space> spaces = spaceDAO.getAll();

        

        for (Reservation reservation : reservations) {
            if (Objects.equals(reservation.userId, user.id)) {
                for (Space space: spaces) {
                    if (Objects.equals(reservation.spaceId, space.id)) {


                    }
                }

            }
        }

        building.setText(user.getName());
        floor.setText(user.getLastName());
        space_on_floor.setText(user.getEmail());
        startTime.setText(user.getLastName());
        endTime.setText(user.getEmail());
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
