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

    public static List<Space> getSpaces(){
        List<Space> testSpaces = new ArrayList<>();
        Space werkplek_1 = new Space(UUID.randomUUID(), 1,"werkplek_1");
        Space vergaderruimte_1 = new Space(UUID.randomUUID(),10,"sponge", "vergaderingruimte_1");
        testSpaces.add(werkplek_1);
        testSpaces.add(vergaderruimte_1);
        return testSpaces;
    }

    public static Space getWorkSpaceFromID(UUID id){
        return new Space(id, 1,"workspace_1");
    }
    public static Space getMeetingroomFromID(UUID id){
        return new Space(id, 1,"sponge","meetingroom_1");
    }


    public static Building getBuildingFromID(UUID id){
        return new Building(id, "Cool Kids Club", "IL2016", "Leiden", "naast het station");
    }

    public static void POST_Building(Building building){

    }

    public static void PATCH_Building(Building building){

    }

    public static User getUserInfoFromID(UUID id){
        return new User(id, "Michael", "Afton", "FredbearEntertainment.Nightshift@gmail.com");
    }
}
