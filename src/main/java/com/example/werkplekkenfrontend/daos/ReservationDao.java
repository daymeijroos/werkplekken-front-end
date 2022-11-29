package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ReservationDao implements Dao<Reservation> {
    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public ReservationDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ArrayList<Reservation> getAll() throws JsonProcessingException {
        String url = "/api/reservation";
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), new TypeReference<>() {});
    }

    @Override
    public Reservation get(String id) throws JsonProcessingException {
        String url = "/api/reservation/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), Reservation.class);
    }

    public ArrayList<Reservation> getAllByUser(String id) throws JsonProcessingException {
        String url = "/api/reservation/user/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), new TypeReference<>() {});
    }

    @Override
    public int post(Reservation object) throws JsonProcessingException {
        String url = "/api/reservation";
        String json = objectMapper.writeValueAsString(object);
        return httpService.postWithURLandJSONreturnsCode(url, json).statusCode();
    }

    @Override
    public void patch(Reservation object) throws JsonProcessingException {
        String url = "/api/reservation/" + object.id;
        String json = objectMapper.writeValueAsString(object);
        httpService.patchWithURL(url, json).statusCode();
    }

    @Override
    public int delete(Reservation object) {
        String url = "/api/reservation/" + object.id;
        return httpService.deleteWithURL(url).statusCode();
    }
}
