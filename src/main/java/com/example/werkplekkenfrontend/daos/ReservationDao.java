package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.UUID;

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
        HttpResponse<String> response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation get(UUID id) {
        String url = "/api/reservation/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        Reservation reservation = null;
        try {
            reservation = objectMapper.readValue(response.body(), Reservation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }

    public ArrayList<Reservation> getAllByUser(String id) {
        String url = "/api/reservation/user/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        try {
            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int post(Reservation object) {
        String url = "/api/reservation";
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            return httpService.postWithURLandJSONreturnsCode(url, json).statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int patch(Reservation object) {
        String url = "/api/reservation/" + object.id;
        try {
            String json = objectMapper.writeValueAsString(object);
            return httpService.patchWithURL(url, json).statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int delete(Reservation object) {
        String url = "/api/reservation/" + object.id;
        return httpService.deleteWithURL(url).statusCode();
    }
}
