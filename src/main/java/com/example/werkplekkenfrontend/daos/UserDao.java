package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.project_settings;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.UUID;

public class UserDao implements Dao<User> {
    public User getCurrent() {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/user/info";
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(response, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return user;
    }

    @Override
    public ArrayList<User> getAll() {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/user";
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = null;
        try {
            users = mapper.readValue(response, new TypeReference<ArrayList<User>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        };
        return users;
    }

    @Override
    public User get(UUID id) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/user/" + id;
        String response = http.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(response, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return user;
    }

    @Override
    public int post(User object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/user";
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
    public int patch(User object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/user/" + object.getId();
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
    public int delete(User object) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/user/" + object.getId();
        return http.deleteWithURL(url);
    }
}
