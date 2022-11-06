package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.models.Facility;
import com.example.werkplekkenfrontend.models.Floor;
import com.example.werkplekkenfrontend.models.Space;
import com.example.werkplekkenfrontend.project_settings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class SpaceDAO implements Dao<Space> {
    private Space SpaceFromJSON(JSONObject objectJSON) {
        Space space = new Space();
        space.setId(objectJSON.getString("id"));
        space.setCapacity(objectJSON.getInt("capacity"));
        space.setFloor((Floor)objectJSON.get("floor"));
        space.setFacilities((Set<Facility>)objectJSON.get("facilities"));
        return space;
    }

    private String fetchResponseBodyFromURL(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
        } catch(Exception e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Space> getAll() {
        ArrayList<Space> spaces = new ArrayList<>();

        String url = project_settings.baseURL + "/api/space/";
        String responseBody = fetchResponseBodyFromURL(url);
        if (responseBody != null) {
            JSONArray responseJSON = new JSONArray(responseBody);
            try {
                for (int i = 0; i < responseJSON.length(); i++) {
                    JSONObject objectJSON = responseJSON.getJSONObject(i);
                    Space space = SpaceFromJSON(objectJSON);

                    spaces.add(space);
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Response body empty.");
        }

        return spaces;
    }

    @Override
    public Space get(UUID id) {
        Space space = new Space();

        String url = project_settings.baseURL + "/api/space/" + id;
        String responseBody = fetchResponseBodyFromURL(url);
        assert responseBody != null;
        JSONArray responseJSON = new JSONArray(responseBody);

        try {
            JSONObject objectJSON = responseJSON.getJSONObject(0);
            space = SpaceFromJSON(objectJSON);
        } catch(Exception e){
            e.printStackTrace();
        }
        return space;
    }

    @Override
    public int post(Space space) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/api/space/" + space.getId());
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            String format = """
                            {
                                id: {0},
                                capacity: {1},
                                floor: {2},
                                facilities: {3}
                            }""";
            String json = String.format(format, space.getId(), space.getCapacity(), space.getFloor(), space.getFacilities());

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    //WE DO NOT CARE ABOUT PATCH
    @Override
    public int patch(Space space) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/api/space/" + space.getId());
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("PATCH");
            http.setDoOutput(true);

            String format = """
                    [
                        { "op": "replace", "path": "/capacity", "value": "{0}" },
                        { "op": "replace", "path": "/floor", "value": "{1}" },
                        { "op": "replace", "path": "/facilities", "value": "{2}" },
                    ]""";
            String json = String.format(format, space.getCapacity(), space.getFloor(), space.getFacilities());

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json-patch+json");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public int delete(Space space) {
        int response = 0;
        try {
            URL url = new URL(project_settings.baseURL + "/space" + space.getId());
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
