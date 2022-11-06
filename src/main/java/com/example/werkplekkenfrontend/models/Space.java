package com.example.werkplekkenfrontend.models;

import java.util.Set;

public class Space {
    public String id;
    public int capacity;
    public Floor floor;
    public Set<Facility> Facilities;

    public Space() {}

    public Space(String id, int capacity, Floor floor, Set<Facility> facilities) {
        this.id = id;
        this.capacity = capacity;
        this.floor = floor;
        Facilities = facilities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Set<Facility> getFacilities() {
        return Facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        Facilities = facilities;
    }
}
