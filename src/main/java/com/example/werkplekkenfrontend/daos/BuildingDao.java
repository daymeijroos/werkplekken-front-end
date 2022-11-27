package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.UUID;

public class BuildingDao implements Dao<Building>{
    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public BuildingDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ArrayList<Building> getAll() {
        String url = "/api/building";
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
    public Building get(UUID id) {
        String url = "/api/building/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Building building = null;
        try {
            building = mapper.readValue(response.body(), Building.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return building;
    }

    @Override
    public int post(Building object) {
        String url = "/api/building";
        try {
            String json = objectMapper.writeValueAsString(object);
            return httpService.postWithURLandJSONreturnsCode(url, json).statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int patch(Building object) {
        String url = "/api/building/" + object.getId();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            return httpService.patchWithURL(url, json).statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int delete(Building object) {
        String url = "/api/building/" + object.getId();
        return httpService.deleteWithURL(url).statusCode();
    }
}
