package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class SpaceDao implements Dao<Space> {

    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public SpaceDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ArrayList<Space> getAll() throws JsonProcessingException {
        String url = "/api/space";
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), new TypeReference<>() {});
    }

    public ArrayList<Space> getAllByFloorId(String floorId) throws JsonProcessingException {
        String url = "/api/space/floor/" + floorId;
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), new TypeReference<>() {});
    }

    @Override
    public Space get(String id) throws JsonProcessingException {
        String url = "/api/space/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), Space.class);
    }

    @Override
    public int post(Space object) throws JsonProcessingException {
        String url = "/api/space";
        String json = objectMapper.writeValueAsString(object);
        return httpService.postWithURLandJSONreturnsCode(url, json).statusCode();
    }

    @Override
    public void patch(Space object) throws JsonProcessingException {
        String url = "/api/space/" + object.getId();
        String json = objectMapper.writeValueAsString(object);
        httpService.patchWithURL(url, json).statusCode();
    }

    @Override
    public int delete(Space object) {
        String url = "/api/space/" + object.getId();
        return httpService.deleteWithURL(url).statusCode();
    }
}