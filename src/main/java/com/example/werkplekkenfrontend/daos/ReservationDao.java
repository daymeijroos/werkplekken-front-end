package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.project_settings;

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
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        String url = project_settings.baseURL + "/v1/api/reservation/";
        String responseBody = fetchResponseBodyFromURL(url);
        JSONArray responseJSON = new JSONArray(responseBody);

        try {
            for (int i = 0; i < responseJSON.length(); i++) {
                JSONObject objectJSON = responseJSON.getJSONObject(i);
                Reservation reservation = ReservationFromJSON(objectJSON);

                reservations.add(reservation);
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }

    @Override
    public Reservation get(UUID id) {
        Reservation reservation = new Reservation();

        String url = project_settings.baseURL + "/reservation/" + id;
        String responseBody = fetchResponseBodyFromURL(url);
        JSONArray responseJSON = new JSONArray(responseBody);

        try {
            JSONObject objectJSON = responseJSON.getJSONObject(0);
            reservation = ReservationFromJSON(objectJSON);
        } catch(Exception e){
            System.out.println(e);
        }
        return reservation;
    }

    @Override
    public int post(Reservation object) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/reservation/" + object.id);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            String format = """     
                            {
                                id: {0},
                                userId: {1},
                                dateIn: {2},
                                dateOut: {3},
                                amountOfPeople: {4}, 
                                spaceId: {5}
                            }""";
            String json = String.format(format, object.id, object.userId, object.dateIn, object.dateOut, object.amountOfPeople, object.spaceId);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    //WE DO NOT CARE ABOUT PATCH
    @Override
    public int patch(Reservation object) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/reservation/" + object.id);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("PATCH");
            http.setDoOutput(true);

            String format = """
                    [
                        { "op": "replace", "path": "/dateIn", "value": "{0}" },
                        { "op": "replace", "path": "/dateOut", "value": "{1}" },
                        { "op": "replace", "path": "/user_id", "value": "{2}" },
                        { "op": "replace", "path": "/space_id", "value": "{3}" }
                        { "op": "replace", "path": "/amountOfPeople", "value": "{4}" }
                    ]""";
            String json = String.format(format,  object.dateIn, object.dateOut, object.userId, object.spaceId, object.amountOfPeople);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    @Override
    public int delete(Reservation object) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/reservation/" + object.id);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }
}
