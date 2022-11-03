package com.example.werkplekkenfrontend.models;

import java.util.Set;

public class Floor {

    String id;

    String Designation;

    Building building;

    Set<Space> spaces;

    public Floor(String id, String designation, Building building, Set<Space> spaces) {
        this.id = id;
        Designation = designation;
        this.building = building;
        this.spaces = spaces;
    }

    public String getId() {
        return id;
    }

    public String getDesignation() {
        return Designation;
    }

    public Building getBuilding() {
        return building;
    }

    public Set<Space> getSpaces() {
        return spaces;
    }
}
