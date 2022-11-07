package com.example.werkplekkenfrontend;

import com.example.werkplekkenfrontend.controllers.*;
import com.example.werkplekkenfrontend.daos.*;
import com.example.werkplekkenfrontend.models.*;
import com.example.werkplekkenfrontend.models.enums.ReservationState;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends Application {
    public static SceneController sceneController = new SceneController();
    public static Stage publicStage;
    public static LoginController loginController = new LoginController(new LoginDao(new HttpService(), new ObjectMapper()), new UserDao(new HttpService(), new ObjectMapper()));

    @Override
    public void start(Stage stage) {
        loginController.register("day", "meijroos", "daymeijroos4@gmail.com", "DumbShit");
//        SpaceDao spaceDao = new SpaceDao(new HttpService(), new ObjectMapper());
//        FloorDao floorDao = new FloorDao(new HttpService(), new ObjectMapper());
//        BuildingDao buildingDao = new BuildingDao(new HttpService(), new ObjectMapper());
//        ReservationDao reservationDao = new ReservationDao(new HttpService(), new ObjectMapper());
//        UserDao userDao = new UserDao(new HttpService(), new ObjectMapper());
//        System.out.println(buildingDao.post(new Building(UUID.randomUUID(), "building", "2233ab", "springvandebrugaf", "brug nee 3")));
//        ArrayList<Building> buildings = buildingDao.getAll();
//
//        for (Building building: buildings) {
//            System.out.println(floorDao.post(new Floor("ja", "floor 1", building.getId().toString())));
//        }
//        ArrayList<Floor> floors = floorDao.getAll();
//
//        for (Floor floor: floors) {
//            System.out.println(spaceDao.post(new Space(UUID.randomUUID(), 2, floor.getId())));
//        }
//        ArrayList<Space> spaces = spaceDao.getAll();
//
//        String dateIn = "1970-01-20T07:16:16.000+00:00";
//        String dateOut = "1970-01-20T07:32:56.000+00:00";
//        User user = userDao.getCurrent();
//
//        for (Space space: spaces) {
//            System.out.println(reservationDao.post(new Reservation("nee", user.getId(), dateIn, dateOut, ReservationState.OPEN, 5, space.getId())));
//        }

        publicStage = stage;
        Main.sceneController.setStage(stage);
        ViewController controller = sceneController.showView("reservation-view.fxml");
        controller.updateView();
    }

    public static void main(String[] args) {
        Main.sceneController = new SceneController();
        launch();
    }
}