package com.example.werkplekkenfrontend.models;

import java.util.Set;
import java.util.UUID;

public class Space {
    UUID id;

    int capacity;

    String floorId;

    public Space(int capacity) {
        this.capacity = capacity;
    }

    public Space() {
    }

    public UUID getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFloorId() {
        return floorId;
    }

}
