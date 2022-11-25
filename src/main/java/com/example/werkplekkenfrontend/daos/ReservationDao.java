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
    public ArrayList<Reservation> getAll() throws Exception {
        String url = "/api/reservation";
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, new TypeReference<>() {});
    }

    @Override
    public Reservation get(UUID id) throws Exception {
        String url = "/api/reservation/" + id;
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Reservation reservation = mapper.readValue(response, Reservation.class);
        return reservation;
    }

    public ArrayList<Reservation> getAllByUser(String id) throws Exception {
        String url = "/api/reservation/user/" + id;
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Reservation> reservations = mapper.readValue(response, new TypeReference<>() {});
        return reservations;
    }

    @Override
    public int post(Reservation object) throws Exception {
        String url = "/api/reservation";
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        return httpService.postWithURLandJSONreturnsCode(url, json);
    }

    @Override
    public int patch(Reservation object) throws Exception {
        String url = "/api/reservation/" + object.id;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        return httpService.patchWithURL(url, json);
    }

    @Override
    public int delete(Reservation object) throws Exception {
        String url = "/api/reservation/" + object.id;
        return httpService.deleteWithURL(url);
    }
}
