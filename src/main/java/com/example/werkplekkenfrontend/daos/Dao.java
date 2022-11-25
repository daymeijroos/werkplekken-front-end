package com.example.werkplekkenfrontend.daos;

import java.util.ArrayList;
import java.util.UUID;

public interface Dao<Type> {
    ArrayList<Type> getAll() throws Exception;
    Type get(UUID id) throws Exception;
    int post(Type object) throws Exception;
    int patch(Type object) throws Exception;
    int delete(Type object) throws Exception;
}