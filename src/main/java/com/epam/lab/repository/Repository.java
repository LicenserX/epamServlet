package com.epam.lab.repository;

import com.epam.lab.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Repository {

    private static final HashMap<Integer, Entity> MAP = new HashMap<>(10000);
    private static Repository repository;
    private int id;

    private Repository() {
    }

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public final Entity save(Entity entity) {
        if (entity.getId() == 0) {
            id++;
            entity.setId(id);
            MAP.put(id, entity);
        } else if (!MAP.containsKey(entity.getId())) {
            MAP.put(entity.getId(), entity);
        }
        return entity;
    }

    public final Entity findById(Integer id) {
        return MAP.get(id);
    }

    public Entity update(Entity entity) {
        if (MAP.containsKey(entity.getId())) {
            MAP.put(entity.getId(), entity);
        }
        return entity;
    }

    public final List<Entity> findAll() {
        return new ArrayList<>(MAP.values());
    }

    public final Entity deleteById(Integer id) {
        if (MAP.containsKey(id)) {
            return MAP.remove(id);
        }
        return null;
    }
}

