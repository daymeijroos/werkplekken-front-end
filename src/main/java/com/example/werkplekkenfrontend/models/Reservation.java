package com.example.werkplekkenfrontend.models;

import com.example.werkplekkenfrontend.models.enums.ReservationState;

import java.util.UUID;

public class Reservation {
    public String id;
    public String userId;
    public String dateIn;
    public String dateOut;
    public ReservationState reservationState;
    public int amountOfPeople;
    public UUID spaceId;

    public Reservation() {}

    public Reservation(String id, String userId, String dateIn, String dateOut, ReservationState reservationState, int amountOfPeople, UUID spaceId) {
        this.id = id;
        this.userId = userId;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.amountOfPeople = amountOfPeople;
        this.spaceId = spaceId;
        this.reservationState = reservationState;
    }

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

    public ReservationState getReservationState() {
        return reservationState;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public UUID getSpaceId() {
        return spaceId;
    }
}
