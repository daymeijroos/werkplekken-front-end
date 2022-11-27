package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.UUID;

public class SpaceDao implements Dao<Space> {

    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public SpaceDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ArrayList<Space> getAll() {
        String url = "/api/space";
        HttpResponse<String> response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Space> getAllByFloorId(String floorId) {
        String url = "/api/space/floor/" + floorId;
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
    public Space get(UUID id) {
        String url = "/api/space/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        Space space = null;
        try {
            space = objectMapper.readValue(response.body(), Space.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return space;
    }

    @Override
    public int post(Space object) {
        String url = "/api/space";
        try {
            String json = objectMapper.writeValueAsString(object);
            return httpService.postWithURLandJSONreturnsCode(url, json).statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int patch(Space object) {
        String url = "/api/space/" + object.getId();
        try {
            String json = objectMapper.writeValueAsString(object);
            return httpService.patchWithURL(url, json).statusCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int delete(Space object) {
        String url = "/api/space/" + object.getId();
        return httpService.deleteWithURL(url).statusCode();
    }
}