package com.example.werkplekkenfrontend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Space {

    public String id;
    public int capacity;
    public String floorId;

    public Space(int capacity) {
        this.capacity = capacity;
    }
}