package com.epam.lab.model;

import java.io.Serializable;

public class Entity implements Serializable {

    private int id;
    private String name;

    public Entity() {
    }

    public Entity(Entity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
