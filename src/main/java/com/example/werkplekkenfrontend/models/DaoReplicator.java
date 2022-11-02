package com.example.werkplekkenfrontend.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DaoReplicator {
    public static List<Building> getBuildings(){
        List<Building> testBuildings = new ArrayList<>();
        Building leiden = new Building(UUID.randomUUID(), "Cool Kids Club", "IL2016", "Leiden", "naast het station");
        Building denHaag = new Building(UUID.randomUUID(), "Sad Kids Club", "HELL00", "Den Haag", "somewhere over the rainbow");
        testBuildings.add(leiden);
        testBuildings.add(denHaag);
        return testBuildings;
    }

    public static Building getBuildingFromID(UUID id){
        return new Building(id, "Cool Kids Club", "IL2016", "Leiden", "naast het station");
    }

    public static void POST_Building(Building building){

    }

    public static void PATCH_Building(Building building){

    }
}
