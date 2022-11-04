package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.project_settings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

public class BuildingDao implements Dao<Building>{
    private Building BuildingFromJSON(JSONObject objectJSON) {
        Building building = new Building();
        building.setId(UUID.fromString(objectJSON.getString("id")));
        building.setName(objectJSON.getString("name"));
        building.setZipcode(objectJSON.getString("zipcode"));
        building.setCity(objectJSON.getString("city"));
        building.setAdress(objectJSON.getString("adress"));
        return building;
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
    public ArrayList<Building> getAll() {
        ArrayList<Building> buildings = new ArrayList<>();

        String url = project_settings.baseURL + "/api/building/";
        String responseBody = fetchResponseBodyFromURL(url);
        JSONArray responseJSON = new JSONArray(responseBody);

        try {
            for (int i = 0; i < responseJSON.length(); i++) {
                JSONObject objectJSON = responseJSON.getJSONObject(i);
                Building building = BuildingFromJSON(objectJSON);

                buildings.add(building);
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return buildings;
    }

    @Override
    public Building get(UUID id) {
        Building buildings = new Building();

        String url = project_settings.baseURL + "/building/" + id;
        String responseBody = fetchResponseBodyFromURL(url);
        JSONArray responseJSON = new JSONArray(responseBody);

        try {
            JSONObject objectJSON = responseJSON.getJSONObject(0);
            buildings = BuildingFromJSON(objectJSON);
        } catch(Exception e){
            System.out.println(e);
        }
        return buildings;
    }

    @Override
    public int post(Building object) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/building/" + object.getId().toString());
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            String format = """     
                            {
                                id: {0},
                                name: {1},
                                zipcode: {2},
                                city: {3},
                                adress: {4}
                            }""";
            String json = String.format(format, object.getId().toString(), object.getName(), object.getZipcode(), object.getCity(), object.getAdress());

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
    public int patch(Building object) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/building/" + object.getId().toString());
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("PATCH");
            http.setDoOutput(true);

            String format = """
                    [
                        { "op": "replace", "path": "/name", "value": "{0}" },
                        { "op": "replace", "path": "/zipcode", "value": "{1}" },
                        { "op": "replace", "path": "/city", "value": "{2}" },
                        { "op": "replace", "path": "/adress", "value": "{3}" }
                    ]""";
            String json = String.format(format,  object.getName(), object.getZipcode(), object.getCity(), object.getAdress());

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
    public int delete(Building object) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/building/" + object.getId().toString());
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
