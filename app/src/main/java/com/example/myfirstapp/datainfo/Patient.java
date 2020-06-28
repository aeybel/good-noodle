package com.example.myfirstapp.datainfo;

public class Patient {

    private String firstName;
    private String lastName;
    // TODO: add pfp prop

    // mandatory constructor
    public Patient() {}

    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
