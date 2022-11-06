package com.example.werkplekkenfrontend.models;

import java.util.UUID;

public class User {
    public String id;
    public String name;
    public String lastName;
    public String email;

    public User() {};

    public User(String id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
