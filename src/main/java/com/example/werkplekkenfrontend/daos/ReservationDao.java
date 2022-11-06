package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.project_settings;

import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ReservationDao implements Dao<Reservation> {
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
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/reservation";
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Reservation> Reservations = null;
        try {
            Reservations = mapper.readValue(response, new TypeReference<ArrayList<Reservation>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        };
        return Reservations;
    }

    @Override
    public Reservation get(UUID id) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/reservation/" + id;
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Reservation reservation = null;
        try {
            reservation = mapper.readValue(response, Reservation.class);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return reservation;
    }

    @Override
    public int post(Reservation object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/reservation";
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
        return http.postWithURLandJSONreturnsCode(url, json);
    }

    @Override
    public int patch(Reservation object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/reservation/" + object.getId();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
        return http.patchWithURL(url, json);
    }


    @Override
    public int delete(Reservation object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/reservation/" + object.getId();
        return http.deleteWithURL(url);
    }
}
