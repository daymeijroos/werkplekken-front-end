package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.Main;
import com.example.werkplekkenfrontend.daos.*;
import com.example.werkplekkenfrontend.elements.NavBarElement;
import com.example.werkplekkenfrontend.models.*;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.UUID;

import static java.lang.String.valueOf;

public class ReservationEditViewController  implements ViewController{


    BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());
    FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());

    private final UserDao userDao = new UserDao(new HttpService(), new ObjectMapper());
    private final ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());

    public String reservationID = null;

    public HBox building_selector_container;
    public HBox floor_selector_container;
    public HBox space_selector_container;
    public HBox datein_selector_container;
    public HBox dateout_selector_container;
    public VBox reservation_container;
    public HBox people_selector_container;
    public VBox main_container;
    public TextField datein;
    public TextField dateout;
    public TextField people;
    public ComboBox<Object> buildingOptions;
    public ComboBox<Object> floorOptions;
    public ComboBox<Object> spaceOptions;


    private void updateReservationDetails(Reservation reservation) {
        datein.setText(String.valueOf(reservation.getDateIn()));
        dateout.setText(String.valueOf(reservation.getDateOut()));
        people.setText(String.valueOf(reservation.getAmountOfPeople()));
    }

    private ArrayList<String> getBuildingNames(){
        ArrayList<String> buildingNames = new ArrayList<>();
        try {
            ArrayList<Building> buildings = buildingDao.getAll();
            for (Building building : buildings){
                buildingNames.add(building.getName());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return buildingNames;
    }

    private ArrayList<String> getFloorDesignations(String buildingId){
        ArrayList<String> floorDesignations = new ArrayList<>();
        try {
            ArrayList<Floor> floors = floorDao.getAllByBuildingId(buildingId);
            for (Floor floor : floors){
                floorDesignations.add(floor.getDesignation());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return floorDesignations;
    }

    private ArrayList<String> getSpaceCapacities(String floorId){
        ArrayList<String> spaceCapacities = new ArrayList<>();
        try {
            ArrayList<Space> spaces = spaceDao.getAllByFloorId(floorId);
            for (Space space : spaces){
                String capacity = valueOf(space.getCapacity());
                if (spaceCapacities.contains(capacity)) continue;
                spaceCapacities.add(capacity);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return spaceCapacities;
    }

    private void setBuildingOptions(){
        buildingOptions = new ComboBox<>(FXCollections.observableArrayList(getBuildingNames()));

        EventHandler<ActionEvent> buildingEvent =
                e -> {
                    String chosenBuildingName = buildingOptions.getValue().toString();
                    String buildingId;

                    try {
                        ArrayList<Building> buildings = buildingDao.getAll();
                        for (Building building : buildings){
                            if (building.getName().equals(chosenBuildingName)){
                                buildingId = building.getId();
                                setFloorOptions(buildingId);
                            }
                        }
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                };

        buildingOptions.setOnAction(buildingEvent);

        building_selector_container.getChildren().add(buildingOptions);
    }

    private void setFloorOptions(String buildingId){
        floorOptions = new ComboBox<>(FXCollections.observableArrayList(getFloorDesignations(buildingId)));

        EventHandler<ActionEvent> floorEvent =
                e -> {
                    String chosenFloorName = floorOptions.getValue().toString();
                    String floorId;

                    try {
                        ArrayList<Floor> floors = floorDao.getAll();
                        for (Floor floor : floors){
                            if (floor.getDesignation().equals(chosenFloorName)){
                                floorId = floor.getId();
                                setSpaceOptions(floorId);
                            }
                        }
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }

                };

        floorOptions.setOnAction(floorEvent);

        ObservableList<javafx.scene.Node> children = floor_selector_container.getChildren();
        if (children.size() > 1){
            children.remove(1);
        }
        floor_selector_container.getChildren().add(floorOptions);
    }

    private void setSpaceOptions(String floorId){
        spaceOptions = new ComboBox<>(FXCollections.observableArrayList(getSpaceCapacities(floorId)));

        EventHandler<ActionEvent> spaceEvent =
                e -> System.out.println(spaceOptions.getValue());

        spaceOptions.setOnAction(spaceEvent);

        ObservableList<javafx.scene.Node> children = space_selector_container.getChildren();
        if (children.size() > 1){
            children.remove(1);
        }
        space_selector_container.getChildren().add(spaceOptions);
    }

    @Override
    public void updateView() {
        setBuildingOptions();
        main_container.getChildren().add(new NavBarElement().getBuildingBox());
        if (reservationID != null) {
            try {
                Reservation reservationFromDao = reservationDao.get(UUID.fromString(reservationID).toString());
                updateReservationDetails(reservationFromDao);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
    }

    public void onReturn() {
        //return to reservation view
        ReservationViewController controller = (ReservationViewController) Main.sceneController.showView("reservation-view.fxml");
        controller.updateView();
    }

    public void onAdd() throws JsonProcessingException {
        //adds edited reservation
        String Selected_building_name = (String) buildingOptions.getSelectionModel().getSelectedItem();
        String Selected_floor_name = (String) floorOptions.getSelectionModel().getSelectedItem();
        String Selected_space_number_string = (String) spaceOptions.getSelectionModel().getSelectedItem();

        int Selected_space_number = Integer.parseInt(Selected_space_number_string);

        String spaceId;

        ArrayList<Building> buildings = buildingDao.getAll();
        ArrayList<Floor> floors = floorDao.getAll();
        ArrayList<Space> spaces = spaceDao.getAll();

        User user = userDao.getCurrent();
        for (Building building : buildings) {
            for (Floor floor : floors) {
                for (Space space : spaces) {
                    if ((building.getName().equals(Selected_building_name)) && (floor.getDesignation().equals(Selected_floor_name) && (space.getCapacity() == (Selected_space_number)))) {
                        spaceId = space.getId();
                        if (reservationID != null) {
                            Reservation updatedReservation = new Reservation(reservationID, user.getId(), datein.getText(), dateout.getText(), Integer.parseInt(people.getText()), spaceId, "OPEN");
                            reservationDao.patch(updatedReservation);
                            break;
                        } else {
                            Reservation newReservation = new Reservation(UUID.randomUUID().toString(), user.getId(), datein.getText(), dateout.getText(), Integer.parseInt(people.getText()), spaceId, "OPEN");
                            reservationDao.post(newReservation);
                            break;
                        }
                    }
                }
            }
        }

        //returns to reservation view
        ReservationViewController controller = (ReservationViewController) Main.sceneController.showView("reservation-view.fxml");
        controller.updateView();
    }
}
