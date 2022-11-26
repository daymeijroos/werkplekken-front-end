package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.UUID;

public class FloorDao implements Dao<Floor> {
    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public FloorDao(HttpService httpService, ObjectMapper objectMapper){
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ArrayList<Floor> getAll() {
        String url = "/api/floor";
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Floor> floors = mapper.readValue(response, new TypeReference<>() {
            });
            return floors;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Floor> getAllByBuildingId(String id) {
        String url = "/api/floor/building/" + id;
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Floor> floors = mapper.readValue(response, new TypeReference<>() {
            });
            return floors;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Floor get(UUID id) {
        String url = "/api/floor/" + id;
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Floor floor = null;
        try {
            floor = mapper.readValue(response, Floor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return floor;
    }

    @Override
    public int post(Floor object) {
        String url = "/api/floor";
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            return httpService.postWithURLandJSONreturnsCode(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int patch(Floor object) {
        String url = "/api/floor/" + object.getId();
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
    public int delete(Floor object) {
        String url = "/api/floor/" + object.getId();
        return httpService.deleteWithURL(url);
    }
}
