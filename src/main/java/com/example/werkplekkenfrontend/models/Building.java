package com.example.werkplekkenfrontend.models;

import java.util.UUID;

public class Building {
    UUID id;
    String name;
    String zipcode;
    String city;
    String address;

    public Building(UUID id, String name, String zipcode, String city, String address) {
        this.id = id;
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
        this.address = address;
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

    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address;
    }
}
