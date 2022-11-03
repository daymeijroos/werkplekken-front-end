package com.example.werkplekkenfrontend.models;

import java.util.Set;
import java.util.UUID;

public class Space {
    UUID id;

    int capacity;

    Floor floor;

    Set<Facility> Facilities;

    public Space(UUID id, int capacity, Floor floor, Set<Facility> facilities) {
        this.id = id;
        this.capacity = capacity;
        this.floor = floor;
        Facilities = facilities;
    }

    public UUID getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Floor getFloor() {
        return floor;
    }

    public Set<Facility> getFacilities() {
        return Facilities;
    }
}
