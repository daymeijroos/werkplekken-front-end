package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.AuthRequest;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginDao {

    private final HttpService httpService;
    private final ObjectMapper objectMapper;

    public LoginDao(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    public String login(String email, String password) throws Exception {
        String endpoint = "/api/auth/login";
        AuthRequest authRequest = new AuthRequest(email, password);
        return httpService.postWithURLandJSONreturnsString(endpoint, objectMapper.writeValueAsString(authRequest));
    }

    public String register(String firstName, String lastName, String email, String password) throws Exception {
        String endpoint = "/api/auth/register";
        AuthRequest registerRequest = new AuthRequest(firstName, lastName, email, password);
        return httpService.postWithURLandJSONreturnsString(endpoint, objectMapper.writeValueAsString(registerRequest));
    }
}
