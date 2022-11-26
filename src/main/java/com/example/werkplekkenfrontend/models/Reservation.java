package com.example.werkplekkenfrontend.models;

import java.lang.reflect.Constructor;
import java.util.UUID;

public class Reservation {
    public Reservation() {}

    public Reservation(String id, String userId, String dateIn, String dateOut, int amountOfPeople, String spaceId, String state) {
        this.id = id;
        this.userId = userId;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amountOfPeople = amountOfPeople;
        this.spaceId = spaceId;
        this.state = state;
    }

    public String id;
    public String userId;
    public String dateIn;
    public String dateOut;
    public int amountOfPeople;
    public String spaceId;
    public String state;
}
