package org.acme.relationships.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Child extends PanacheEntity {
    private String name;

    public static Child findByName(String name){
        return find("name", name).firstResult();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
