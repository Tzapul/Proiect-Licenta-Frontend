package com.example.tzapt.models;

import java.io.Serializable;

/**
 * Created by tzapt on 6/24/2017.
 */

public class Client implements Serializable {

    private int id;

    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
