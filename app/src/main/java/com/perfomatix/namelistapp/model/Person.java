package com.perfomatix.namelistapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("fname")
    @Expose
    private String firstName;

    @SerializedName("lname")
    @Expose
    private String lastName;

    @SerializedName("city")
    @Expose
    private String city;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }
}
