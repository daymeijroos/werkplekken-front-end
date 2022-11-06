package com.example.werkplekkenfrontend.models;

public class Facility {
    private String id;

    private String name;

    private int quantity;

    private Building building;

    public Facility(String id, String name, int quantity, Building building) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.building = building;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Building getBuilding() {
        return building;
    }
}