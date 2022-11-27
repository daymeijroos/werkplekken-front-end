package com.example.werkplekkenfrontend.elements;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.controllers.ReservationEditViewController;
import com.example.werkplekkenfrontend.controllers.ReservationViewController;
import com.example.werkplekkenfrontend.controllers.ViewController;
import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.daos.FloorDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class ReservationElement {
    SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
    FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());
    ReservationViewController parent;
    Reservation reservation;

    public String buildingId;
    public String floorId;

    private String buildingName;
    private String floorName;

    private HBox reservationContainer;

    private Label building;
    private Label floor;
    private Label reservationSize;
    private Label dateIn;
    private Label dateOut;
    private Label state;
    private Button cancel;
    private Button checkIn;
    private Button edit;

    public ReservationElement (ReservationViewController parent, Reservation reservation){
        this.parent = parent;
        this.reservation = reservation;
        getInformationFromDaos();
        createElements();
        createContainers();
        reservationContainer.setMaxWidth(800);
        reservationContainer.setStyle("-fx-padding: 50; -fx-border-color: black");
        edit.setOnAction(actionEvent -> parent.onEditButtonClick(this.reservation));
        cancel.setOnAction(actionEvent -> parent.onCancelReservation(this.reservation));
    }

    private void getInformationFromDaos(){
        floorId = spaceDao.get(UUID.fromString(reservation.spaceId)).getFloorId();
        buildingId = floorDao.get(UUID.fromString(floorId)).getBuildingId();

        buildingName = buildingDao.get(UUID.fromString(buildingId)).getName();
        floorName = floorDao.get(UUID.fromString(floorId)).getDesignation();
    }

    private void createElements(){
        building = new Label(buildingName);
        floor = new Label(floorName);
        reservationSize = new Label("Reservation size: " + reservation.amountOfPeople);
        dateIn = new Label(reservation.dateIn);
        dateOut = new Label(reservation.dateOut);
        state = new Label(reservation.state);
        cancel = new Button("cancel");
        checkIn = new Button("check in");
        edit = new Button("edit");

    }

    public void onEditButtonClick(Space space){
        ReservationEditViewController controller = (ReservationEditViewController) Main.sceneController.showView("reservation-edit-view.fxml");
        controller.reservationID = reservation.getId();
        controller.updateView();

    }

    private void createContainers(){
        VBox dateContainer = new VBox(dateIn, dateOut);
        VBox buttonContainer = new VBox(cancel, checkIn,edit);

        VBox leftContainer = new VBox(building, state, dateContainer);
        VBox rightContainer = new VBox(reservationSize, buttonContainer);

        reservationContainer = new HBox(leftContainer, floor, rightContainer);
    }

    public HBox getReservationContainer() {
        return reservationContainer;
    }
}
