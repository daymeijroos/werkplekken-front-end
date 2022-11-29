package com.example.werkplekkenfrontend.daos;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

public interface Dao<Type> {
    ArrayList<Type> getAll() throws JsonProcessingException;
    Type get(String id) throws JsonProcessingException;
    int post(Type object) throws JsonProcessingException;
    void patch(Type object) throws JsonProcessingException;
    int delete(Type object);
}