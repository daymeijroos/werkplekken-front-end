package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Reservation;

import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ReservationDao implements Dao<Reservation> {

    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public ReservationDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    private Reservation ReservationFromJSON(JSONObject objectJSON) {
        Reservation reservation = new Reservation();
        reservation.id = objectJSON.getString("id");
        reservation.userId = objectJSON.getString("userId");
        reservation.dateIn = objectJSON.getLong("dateIn");
        reservation.dateOut = objectJSON.getLong("dateOut");
        reservation.amountOfPeople = objectJSON.getInt("amountOfPeople");
        reservation.spaceId = objectJSON.getString("spaceId");
        return reservation;
    }

    private String fetchResponseBodyFromURL(String url) {
        String responseBody;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            responseBody = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
        } catch(Exception e) {
            System.out.println("ERROR!");
            System.out.println(e);
            return null;
        }

        return responseBody;
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
}
