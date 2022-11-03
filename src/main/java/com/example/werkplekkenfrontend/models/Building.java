package com.example.werkplekkenfrontend.models;

import java.util.UUID;

public class Building {
    UUID id;
    String name;
    String zipcode;
    String city;
    String adress;

    public Building(UUID id, String name, String zipcode, String city, String adress) {
        this.id = id;
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.adress = adress;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getAdress() {
        return adress;
    }
}
