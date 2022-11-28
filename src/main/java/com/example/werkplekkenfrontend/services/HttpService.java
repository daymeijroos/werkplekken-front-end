package com.example.werkplekkenfrontend.services;

import com.example.werkplekkenfrontend.Main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String baseURL = "http://localhost:8081";

    public HttpResponse<String>  postWithURLandJSONreturnsString(String endpoint, String json) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", Main.currentUser.getJWTtoken())
                .uri(URI.create(baseURL + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse<String> postWithURLandJSONreturnsCode(String endpoint, String json) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", Main.currentUser.getJWTtoken())
                .uri(URI.create(baseURL + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse<String> getWithURL(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + endpoint))
                .header("Authorization", Main.currentUser.getJWTtoken())
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse<String> patchWithURL(String endpoint, String json) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", Main.currentUser.getJWTtoken())
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse<String> deleteWithURL(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + endpoint))
                .header("Authorization", Main.currentUser.getJWTtoken())
                .DELETE()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }  catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
