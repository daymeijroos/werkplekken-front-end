package com.example.werkplekkenfrontend.models;

import java.util.UUID;

public class User {
    UUID id;
    String name;
    String lastName;
    String email;

    public User(UUID id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public UUID getId() {
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
