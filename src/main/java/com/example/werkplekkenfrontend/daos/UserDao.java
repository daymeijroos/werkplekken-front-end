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

    public User getCurrent() {
        String url = "/api/user/info";
        String response = httpService.getWithURL(url);
        User user = null;
        try {
            user = mapper.readValue(response, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> getAll() {
        String url = "/api/user";
        String response = httpService.getWithURL(url);
        ArrayList<User> users = null;
        try {
            users = mapper.readValue(response, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User get(UUID id) {
        String url = "/api/user/" + id;
        String response = httpService.getWithURL(url);
        User user = null;
        try {
            user = mapper.readValue(response, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int post(User object) {
        String url = "/api/user";
        try {
            String json = mapper.writeValueAsString(object);
            return httpService.postWithURLandJSONreturnsCode(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int patch(User object) {
        String url = "/api/user/" + object.getId();
        try {
            String json = mapper.writeValueAsString(object);
            return httpService.patchWithURL(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

    @Override
    public int delete(User object) {
        String url = "/api/user/" + object.getId();
        return httpService.deleteWithURL(url);
    }
}
