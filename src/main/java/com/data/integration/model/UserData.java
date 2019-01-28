package com.data.integration.model;

import com.data.integration.utility.StringConvert;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserData implements Serializable {
    private final static long serialVersionUID = -4688958938270901928L;

    @Pattern(regexp="(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)", message = "40006")
    private String email;
    private String firstName;
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringConvert.toUpper(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = StringConvert.toUpper(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = StringConvert.toUpper(lastName);
    }

}