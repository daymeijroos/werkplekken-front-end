package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.project_settings;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.UUID;

public class ReservationDao implements Dao<Reservation> {
    @Override
    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        String responseBody;
        String url = project_settings.baseURL + "/v1/api/reservation/";
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

        JSONArray responseJSON = new JSONArray(responseBody);

        try {
            for (int i = 0; i < responseJSON.length(); i++) {
                JSONObject objectJSON = responseJSON.getJSONObject(i);
                Reservation reservation = new Reservation();

                reservation.id = objectJSON.getString("id");
                reservation.userId = objectJSON.getString("userId");
                reservation.dateIn = objectJSON.getLong("dateIn");
                reservation.dateOut = objectJSON.getLong("dateOut");
                reservation.amountOfPeople = objectJSON.getInt("amountOfPeople");
                reservation.spaceId = objectJSON.getString("spaceId");

                reservations.add(reservation);
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }

    @Override
    public Reservation get(UUID id) {
        return null;
    }

    @Override
    public int post(Reservation object) {
        return 0;
    }

    @Override
    public int patch(Reservation object) {
        return 0;
    }

    @Override
    public int delete(Reservation object) {
        return 0;
    }
}
