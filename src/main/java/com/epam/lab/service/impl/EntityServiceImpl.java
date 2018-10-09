package com.epam.lab.service.impl;

import com.epam.lab.model.Entity;
import com.epam.lab.repository.Repository;
import com.epam.lab.service.EntityService;

import java.util.List;

public class EntityServiceImpl implements EntityService {

    private Repository repository = Repository.getInstance();

    @Override
    public Entity add(Entity entity) {
        return repository.save(entity);
    }

    @Override
    public Entity get(Integer id) {
        return new Entity(repository.findById(id));
    }

    @Override
    public List<Entity> getAll() {
        return repository.findAll();
    }

    @Override
    public Entity update(Entity entity) {
        return repository.update(entity);
    }

    @Override
    public Entity delete(Integer id) {
        return repository.deleteById(id);
    }
}
