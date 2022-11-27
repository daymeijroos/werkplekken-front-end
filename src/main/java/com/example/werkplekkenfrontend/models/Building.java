package com.example.werkplekkenfrontend.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Building {

    private UUID id;
    private String name;
    private String zipcode;
    private String city;
    private String address;

}
