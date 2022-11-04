package com.example.werkplekkenfrontend.daos;

import java.util.List;
import java.util.UUID;

public interface Dao<Type> {
    List<Type> getAll();
    Type get(UUID id);
    int post(Type object);
    int patch(Type object);
    int delete(Type object);
}
