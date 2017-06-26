package com.example.tzapt.models;

/**
 * Created by tzapt on 6/26/2017.
 */

public class DayOff {

    private int id;
    private String date;

    public DayOff(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
