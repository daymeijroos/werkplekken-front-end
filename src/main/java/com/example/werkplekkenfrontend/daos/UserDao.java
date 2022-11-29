package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class UserDao implements Dao<User> {

    private final HttpService httpService;
    private final ObjectMapper objectMapper;


    public UserDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    public User getCurrent() throws JsonProcessingException {
        String url = "/api/user/info";
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), User.class);
    }

    @Override
    public ArrayList<User> getAll() throws JsonProcessingException {
        String url = "/api/user";
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
    }

    @Override
    public User get(String id) throws JsonProcessingException {
        String url = "/api/user/" + id;
        HttpResponse<String> response = httpService.getWithURL(url);
        return objectMapper.readValue(response.body(), User.class);
    }

    @Override
    public int post(User object) throws JsonProcessingException {
        String url = "/api/user";
        String json = objectMapper.writeValueAsString(object);
        return httpService.postWithURLandJSONreturnsCode(url, json).statusCode();

    }

    @Override
    public void patch(User object) throws JsonProcessingException {
        String url = "/api/user/" + object.getId();
        String json = objectMapper.writeValueAsString(object);
        httpService.patchWithURL(url, json).statusCode();
    }

    @Override
    public int delete(User object) {
        String url = "/api/user/" + object.getId();
        return httpService.deleteWithURL(url).statusCode();
    }
}
