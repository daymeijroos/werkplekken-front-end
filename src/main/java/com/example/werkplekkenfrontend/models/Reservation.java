package com.example.werkplekkenfrontend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

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
