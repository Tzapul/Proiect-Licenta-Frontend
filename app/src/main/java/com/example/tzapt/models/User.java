package com.example.tzapt.models;

/**
 * Created by tzapt on 6/26/2017.
 */

public class User extends Client {

    private Account account;
    private PersonDetails personDetails;

    public User(int id, Account account, PersonDetails personDetails) {
        super(id);
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
