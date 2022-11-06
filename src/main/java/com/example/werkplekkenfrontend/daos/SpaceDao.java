package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.models.User;
import com.example.werkplekkenfrontend.project_settings;
import com.example.werkplekkenfrontend.services.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.UUID;

public class SpaceDao implements Dao<Space> {

        public Space getCurrent() {
            HttpService http = new HttpService();
            String url = project_settings.baseURL + "/api/user";
            String response = http.getWithURL(url);
            ObjectMapper mapper = new ObjectMapper();
            Space space = null;
            try {
                space = mapper.readValue(response, Space.class);
            } catch (Exception e) {
                e.printStackTrace();
            };
            return space;
        }

        @Override
        public ArrayList<Space> getAll() {
            HttpService http = new HttpService();
            String url = project_settings.baseURL + "/api/space";
            String response = http.getWithURL(url);
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Space> spaces = null;
            try {
                spaces = mapper.readValue(response, new TypeReference<ArrayList<Space>>(){});
            } catch (Exception e) {
                e.printStackTrace();
            };
            return spaces;
        }

        @Override
        public Space get(UUID id) {
            HttpService http = new HttpService();
            String url = project_settings.baseURL + "/api/space/" + id;
            String response = http.getWithURL(url);
            ObjectMapper mapper = new ObjectMapper();
            Space space = null;
            try {
                space = mapper.readValue(response, Space.class);
            } catch (Exception e) {
                e.printStackTrace();
            };
            return space;
        }

        @Override
        public int post(Space object) {
            HttpService http = new HttpService();
            String url = project_settings.baseURL + "/api/space";
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
        public int patch(Space object) {
            HttpService http = new HttpService();
            String url = project_settings.baseURL + "/api/space/" + object.getId();
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
        public int delete(Space object) {
            HttpService http = new HttpService();
            String url = project_settings.baseURL + "/api/space/" + object.getId();
            return http.deleteWithURL(url);
        }
    }
