package com.example.werkplekkenfrontend.services;

import com.example.werkplekkenfrontend.Main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String baseURL = "http://localhost:8081";

    public String postWithURLandJSONreturnsString(String endpoint, String json) {
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", Main.currentUser.getJWTtoken())
                .uri(URI.create(baseURL + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int postWithURLandJSONreturnsCode(String endpoint, String json) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", Main.currentUser.getJWTtoken())
                .uri(URI.create(baseURL + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::statusCode)
                    .join();
        } catch (Exception e) {
            return 400;
        }
    }

    public String getWithURL(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + endpoint))
                .header("Authorization", Main.currentUser.getJWTtoken())
                .GET()
                .build();
        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int patchWithURL(String endpoint, String json) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", Main.currentUser.getJWTtoken())
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::statusCode)
                    .join();
        } catch (Exception e) {
            return 400;
        }
    }

    public int deleteWithURL(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + endpoint))
                .header("Authorization", Main.currentUser.getJWTtoken())
                .DELETE()
                .build();
        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::statusCode)
                    .join();
        } catch (Exception e) {
            return 400;
        }
    }
}
