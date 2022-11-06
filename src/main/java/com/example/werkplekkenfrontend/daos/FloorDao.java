package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.models.Floor;
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

public class FloorDao implements Dao<Floor> {
    @Override
    public ArrayList<Floor> getAll() {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/floor";
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Floor> floors = null;

        try {
            floors = mapper.readValue(response, new TypeReference<ArrayList<Floor>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        };

        return floors;
    }

    @Override
    public Floor get(UUID id) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/floor/" + id;
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Floor floor = null;

        try {
            floor = mapper.readValue(response, Floor.class);
        } catch (Exception e) {
            e.printStackTrace();
        };

        return floor;
    }

    @Override
    public int post(Floor object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/floor";
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
    public int patch(Floor object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/floor/" + object.getId();
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
    public int delete(Floor object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/floor/" + object.getId();
        return http.deleteWithURL(url);
    }
}
