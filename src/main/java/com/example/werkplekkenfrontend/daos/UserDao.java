package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.UUID;

public class UserDao implements Dao<User> {

    private final HttpService httpService;
    private final ObjectMapper mapper;


    public UserDao(HttpService httpService, ObjectMapper mapper) {
        this.httpService = httpService;
        this.mapper = mapper;
    }

    public User getCurrent() throws Exception {
        String url = "/api/user/info";
        String response = httpService.getWithURL(url);
        User user = null;
        user = mapper.readValue(response, User.class);
        return user;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        String url = "/api/user";
        String response = httpService.getWithURL(url);
        ArrayList<User> users = null;
        users = mapper.readValue(response, new TypeReference<>() {});
        return users;
    }

    @Override
    public User get(UUID id) throws Exception {
        String url = "/api/user/" + id;
        String response = httpService.getWithURL(url);
        User user = null;
        user = mapper.readValue(response, User.class);
        return user;
    }

    @Override
    public int post(User object) throws Exception {
        String url = "/api/user";
        String json = mapper.writeValueAsString(object);
        return httpService.postWithURLandJSONreturnsCode(url, json);
    }

    @Override
    public int patch(User object) throws Exception {
        String url = "/api/user/" + object.getId();
        String json = mapper.writeValueAsString(object);
        return httpService.patchWithURL(url, json);
    }

    @Override
    public int delete(User object) throws Exception {
        String url = "/api/user/" + object.getId();
        return httpService.deleteWithURL(url);
    }
}
