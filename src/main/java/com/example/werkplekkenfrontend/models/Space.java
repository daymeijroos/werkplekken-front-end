package com.example.werkplekkenfrontend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Space {

    public String id;


    public UUID id;
    public int capacity;
    public String floorId;


    public Space(int capacity) {
        this.capacity = capacity;
    }

    public Space(String id, int capacity, String floorId) {
        this.id = id;
        this.capacity = capacity;
        this.floorId = floorId;
    }

    public Space() {
    }
    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFloorId() {
        return floorId;
    }


}