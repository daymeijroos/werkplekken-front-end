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

    public Building(){

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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
