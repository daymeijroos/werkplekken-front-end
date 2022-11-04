package com.example.werkplekkenfrontend.models;

import java.util.UUID;

public class Space {
    UUID id;

    String name;

    int capacity;

    String Facilities;

    public Space(UUID id, int capacity, String facilities, String name) {
        this.id = id;
        this.capacity = capacity;
        this.Facilities = facilities;
        this.name = name;
    }

    public Space(UUID id, int capacity,String name) {
        this.id = id;
        this.capacity = capacity;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFacilities() {
        return Facilities;
    }

    public UUID getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
}
