package com.epam.lab.service;

import com.epam.lab.model.Entity;

import java.util.List;

public interface EntityService {

    Entity add(Entity entity);

    Entity get(Integer id);

    List<Entity> getAll();

    Entity update(Entity entity);

    Entity delete(Integer id);



}
