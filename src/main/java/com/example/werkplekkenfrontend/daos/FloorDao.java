package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class FloorDao implements Dao<Floor> {
    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public FloorDao(HttpService httpService, ObjectMapper objectMapper){
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ArrayList<Floor> getAll() throws JsonProcessingException {
        String url = "/api/floor";
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
    }

    public ArrayList<Floor> getAllByBuildingId(String id) throws JsonProcessingException {
        String url = "/api/floor/building/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
    }

    @Override
    public Floor get(String id) throws JsonProcessingException {
        String url = "/api/floor/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), Floor.class);
    }

    @Override
    public int post(Floor object) throws JsonProcessingException {
        String url = "/api/floor";
        String json = objectMapper.writeValueAsString(object);
        return httpService.postWithURLandJSONreturnsCode(url, json).statusCode();
    }

    @Override
    public void patch(Floor object) throws JsonProcessingException {
        String url = "/api/floor/" + object.getId();
        String json = objectMapper.writeValueAsString(object);
        httpService.patchWithURL(url, json).statusCode();
    }

    @Override
    public int delete(Floor object) {
        String url = "/api/floor/" + object.getId();
        return httpService.deleteWithURL(url).statusCode();
    }
}
