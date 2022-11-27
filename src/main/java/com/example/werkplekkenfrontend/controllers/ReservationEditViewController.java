package com.example.werkplekkenfrontend.controllers;

import com.example.werkplekkenfrontend.daos.BuildingDao;
import com.example.werkplekkenfrontend.daos.FloorDao;
import com.example.werkplekkenfrontend.daos.ReservationDao;
import com.example.werkplekkenfrontend.daos.SpaceDao;
import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ReservationEditViewController  implements ViewController{
    BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());
    FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
    SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
    ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());

    public HBox building_selector_container;
    public HBox floor_selector_container;
    public HBox space_selector_container;

    private ArrayList<String> getBuildingNames(){
        ArrayList<String> buildingNames = new ArrayList<>();
        ArrayList<Building> buildings = buildingDao.getAll();
        for (Building building : buildings){
            buildingNames.add(building.getName());
        }
        return buildingNames;
    }

    private ArrayList<String> getFloorDesignations(String buildingId){
        ArrayList<String> floorDesignations = new ArrayList<>();
        ArrayList<Floor> floors = floorDao.getAllByBuildingId(buildingId);
        for (Floor floor : floors){
            floorDesignations.add(floor.getDesignation());
        }
        return floorDesignations;
    }

    private ArrayList<String> getSpaceCapacities(String floorId){
        ArrayList<String> spaceCapacities = new ArrayList<>();
        ArrayList<Space> spaces = spaceDao.getAllByFloorId(floorId);
        for (Space space : spaces){
            String capacity = valueOf(space.getCapacity());
            if (spaceCapacities.contains(capacity)) continue;
            spaceCapacities.add(capacity);
        }
        return spaceCapacities;
    }

    private void setBuildingOptions(){
        ComboBox buildingOptions = new ComboBox(FXCollections.observableArrayList(getBuildingNames()));

        EventHandler<ActionEvent> buildingEvent =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        String chosenBuildingName = buildingOptions.getValue().toString();
                        String buildingId;

                        ArrayList<Building> buildings = buildingDao.getAll();
                        for (Building building : buildings){
                            if (building.getName().equals(chosenBuildingName)){
                                buildingId = building.getId().toString();
                                setFloorOptions(buildingId);
                            }
                        }
                    }
                };

        buildingOptions.setOnAction(buildingEvent);

        building_selector_container.getChildren().add(buildingOptions);
    }

    private void setFloorOptions(String buildingId){
        ComboBox floorOptions = new ComboBox(FXCollections.observableArrayList(getFloorDesignations(buildingId)));

        EventHandler<ActionEvent> floorEvent =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        String chosenFloorName = floorOptions.getValue().toString();
                        String floorId;

                        ArrayList<Floor> floors = floorDao.getAll();
                        for (Floor floor : floors){
                            if (floor.getDesignation().equals(chosenFloorName)){
                                floorId = floor.getId();
                                setSpaceOptions(floorId);
                            }
                        }
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
        ComboBox spaceOptions = new ComboBox(FXCollections.observableArrayList(getSpaceCapacities(floorId)));

        EventHandler<ActionEvent> spaceEvent =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(spaceOptions.getValue());
                    }
                };

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
    }

    public void onReturn() {
        //return to reservation view
    }
}
