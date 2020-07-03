package com.example.myfirstapp.datainfo;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Patient implements Serializable {

    // is id included? idonthinkso
    //private String id;
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
