package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.project_settings;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class LoginDao {
    public String login(String email, String password) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/auth/login";
        Map<String, String> elements = new HashMap<String, String>();
        elements.put("email", email);
        elements.put("password", password);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
             json = objectMapper.writeValueAsString(elements);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return http.postWithURLandJSONreturnsString(url, json);
    }

    public String register(String firstName, String lastName, String email, String password) {
        HttpService http = new HttpService();
        String url = project_settings.baseURL + "/api/auth/register";
        Map<String, String> elements = new HashMap<String, String>();
        elements.put("name", firstName);
        elements.put("lastName", lastName);
        elements.put("email", email);
        elements.put("password", password);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(elements);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return http.postWithURLandJSONreturnsString(url, json);
    }
}
