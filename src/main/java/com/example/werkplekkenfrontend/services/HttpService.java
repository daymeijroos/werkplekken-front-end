package com.example.werkplekkenfrontend.services;

import com.example.werkplekkenfrontend.Main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {
    public String postWithURLandJSONreturnsString(String urlString, String json) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", Main.loginController.getJWT())
                .uri(URI.create(urlString))
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

    public int postWithURLandJSONreturnsCode(String urlString, String json) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", Main.loginController.getJWT())
                .uri(URI.create(urlString))
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

    public String getWithURL(String urlString) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Authorization", Main.loginController.getJWT())
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

    public int patchWithURL(String urlString, String json) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Authorization", Main.loginController.getJWT())
                .GET()
                .build();
        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::statusCode)
                    .join();
        } catch (Exception e) {
            return 400;
        }
    }

    public int deleteWithURL(String urlString) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Authorization", Main.loginController.getJWT())
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
