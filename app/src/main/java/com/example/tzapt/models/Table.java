package com.example.tzapt.models;

/**
 * Created by tzapt on 6/25/2017.
 */

public class Table {

    private int id;

    private String name;

    private int people;

    public Table(int id, String name, int people) {
        this.id = id;
        this.name = name;
        this.people = people;
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

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
