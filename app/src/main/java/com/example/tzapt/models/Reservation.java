package com.example.tzapt.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tzapt on 6/26/2017.
 */

public class Reservation {

    private int id;

    private String name;

    private String email;

    private String date;

    private String phone;

    private int people;

    private List<String> tablesList = new ArrayList<>();

    public Reservation(int id, String name, String email, String date, String phone, int people, List<String> tablesList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.date = date;
        this.phone = phone;
        this.people = people;
        this.tablesList = tablesList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public List<String> getTablesList() {
        return tablesList;
    }

    public void setTablesList(List<String> tablesList) {
        this.tablesList = tablesList;
    }
}
