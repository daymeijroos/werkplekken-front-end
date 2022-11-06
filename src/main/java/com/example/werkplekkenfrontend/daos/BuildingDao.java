package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.models.Reservation;
import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.project_settings;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Override
    public ArrayList<Building> getAll() {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/building";
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Building> buildings = null;
        try {
            buildings = mapper.readValue(response, new TypeReference<ArrayList<Building>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildings;
    }

    @Override
    public Building get(UUID id) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/building/" + id;
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Building building = null;
        try {
            building = mapper.readValue(response, Building.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return building;
    }

    @Override
    public int post(Building object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/building";
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
    public int patch(Building object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/building/" + object.getId();
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
    public int delete(Building object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/building/" + object.getId();
        return http.deleteWithURL(url);
    }
}
