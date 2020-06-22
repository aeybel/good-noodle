package com.example.myfirstapp;

public class Patient {

    public String firstname;
    public String lastname;
    // profile image here

    // necessary constructor for DataSnapshot.getValue(user.class)
    public Patient() {
    }

    public Patient(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
