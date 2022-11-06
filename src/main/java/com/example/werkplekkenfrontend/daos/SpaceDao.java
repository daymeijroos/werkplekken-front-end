package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Building;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.project_settings;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Space> spaces = mapper.readValue(response, new TypeReference<>() {
            });
            return spaces;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Space get(UUID id) {
        String url = "/api/space/" + id;
        String response = httpService.getWithURL(url);
        ObjectMapper mapper = new ObjectMapper();
        Space space = null;
        try {
            space = mapper.readValue(response, Space.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return space;
    }

    @Override
    public int post(Space object) {
        String url = "/api/space";
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
    public int patch(Space object) {
        String url = "/api/space/" + object.getId();
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
    public int delete(Space object) {
        String url = "/api/space/" + object.getId();
        return httpService.deleteWithURL(url);
    }
}
