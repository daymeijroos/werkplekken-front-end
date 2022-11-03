package com.example.werkplekkenfrontend.models;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.UUID;

public class Reservation {
    public Reservation() {}

    public Reservation(String id, String userId, LocalDate dateIn, LocalDate dateOut, int amountOfPeople, String spaceId) {
        this.id = id;
        this.userId = userId;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amountOfPeople = amountOfPeople;
        this.spaceId = spaceId;
    }

    public String id;
    public String userId;
    public LocalDate dateIn;
    public LocalDate dateOut;
    public int amountOfPeople;
    public String spaceId;
}
