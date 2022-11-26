package com.example.werkplekkenfrontend.models;

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

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getDateIn() {
        return dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public String getState() {
        return state;
    }
}
