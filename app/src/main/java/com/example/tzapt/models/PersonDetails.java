package com.example.tzapt.models;

import java.io.Serializable;

/**
 * Created by tzapt on 6/24/2017.
 */

public class PersonDetails implements Serializable {

    private String firstName;
    private String lastName;
    private String phone;

    public PersonDetails(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
