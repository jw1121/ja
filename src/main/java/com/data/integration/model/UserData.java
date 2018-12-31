package com.data.integration.model;

import java.io.Serializable;

public class UserData implements Serializable
{

    private String email;
    private String firstName;
    private String lastName;

    private final static long serialVersionUID = -4688958938270901928L;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}