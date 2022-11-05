package com.example.werkplekkenfrontend.models;

public class Floor {

    private String id;
    private String designation;
    private String buildingId;

    public Floor() {
    }

    public Floor(String id, String designation, String buildingId) {
        this.id = id;
        this.designation = designation;
        this.buildingId = buildingId;
    }

    public String getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
}
