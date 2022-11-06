package com.example.werkplekkenfrontend.daos;

import com.example.werkplekkenfrontend.project_settings;

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
import java.util.Objects;
import java.util.UUID;

public interface Dao<Type> {
    ArrayList<Type> getAll();
    Type get(UUID id);
    int post(Type object);
    int patch(Type object);
    int delete(Type object);
}