package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Reservation;

import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class ReservationDao implements Dao<Reservation> {

    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public ReservationDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ArrayList<Reservation> getAll() {
        String url = "/api/reservation";
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Reservation> reservations = mapper.readValue(response, new TypeReference<>() {
            });
            return reservations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation get(UUID id) {
        String url = "/api/reservation/" + id;
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Reservation reservation = null;
        try {
            reservation = mapper.readValue(response, Reservation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public int post(Reservation object) {
        String url = "/api/reservation";
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            return httpService.postWithURLandJSONreturnsCode(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    //WE DO NOT CARE ABOUT PATCH
    @Override
    public int patch(Reservation object) {
        String url = "/api/reservation/" + object.getId();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            return httpService.patchWithURL(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int delete(Reservation object) {
        String url = "/api/reservation/" + object.getId();
        return httpService.deleteWithURL(url);
    }

    public Reservation getByUser(UUID id) {
        String url = "/api/reservation/user/" + id;
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Reservation reservation = null;
        try {
            reservation = mapper.readValue(response, Reservation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }
}
