package com.example.tzapt.models;

/**
 * Created by tzapt on 6/24/2017.
 */

public class Client {

    private Account account;
    private PersonDetails personDetails;

    public Client(Account account, PersonDetails personDetails) {
        this.account = account;
        this.personDetails = personDetails;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }
}
