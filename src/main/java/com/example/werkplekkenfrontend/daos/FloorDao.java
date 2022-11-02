package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.models.Floor;
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

public class FloorDao implements Dao<Floor> {

    private Floor FloorFromJSON(JSONObject objectJSON) {
        Floor floor = new Floor();
        floor.setId(objectJSON.getString("id"));
        floor.setBuildingId(objectJSON.getString("buildingId"));
        floor.setDesignation(objectJSON.getString("designation"));
        return floor;
    }

    private String fetchResponseBodyFromURL(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
        } catch(Exception e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Floor> getAll() {
        ArrayList<Floor> floors = new ArrayList<>();

        String url = project_settings.baseURL + "/api/floor/";
        String responseBody = fetchResponseBodyFromURL(url);
            if (responseBody != null) {
                JSONArray responseJSON = new JSONArray(responseBody);
                try {
                    for (int i = 0; i < responseJSON.length(); i++) {
                        JSONObject objectJSON = responseJSON.getJSONObject(i);
                        Floor floor = FloorFromJSON(objectJSON);

                        floors.add(floor);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("Response body empty.");
            }

        return floors;
    }

    @Override
    public Floor get(UUID id) {
        Floor floor = new Floor();

        String url = project_settings.baseURL + "/api/floor/" + id;
        String responseBody = fetchResponseBodyFromURL(url);
        assert responseBody != null;
        JSONArray responseJSON = new JSONArray(responseBody);

        try {
            JSONObject objectJSON = responseJSON.getJSONObject(0);
            floor = FloorFromJSON(objectJSON);
        } catch(Exception e){
            e.printStackTrace();
        }
        return floor;
    }

    @Override
    public int post(Floor floor) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/api/floor/" + floor.getId());
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            String format = """     
                            {
                                id: {0},
                                designation: {1},
                                buildingId: {2}
                            }""";
            String json = String.format(format, floor.getId(), floor.getDesignation(), floor.getBuildingId());

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    //WE DO NOT CARE ABOUT PATCH
    @Override
    public int patch(Floor floor) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/api/floor/" + floor.getId());
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("PATCH");
            http.setDoOutput(true);

            String format = """
                    [
                        { "op": "replace", "path": "/designation", "value": "{0}" },
                        { "op": "replace", "path": "/buildingId", "value": "{1}" },
                    ]""";
            String json = String.format(format, floor.getDesignation(), floor.getBuildingId());

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json-patch+json");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public int delete(Floor floor) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/floor" + floor.getId());
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
