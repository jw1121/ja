package com.data.integration.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Parcel implements Serializable {
    private final static long serialVersionUID = 6522564300089597000L;

    @NotNull
    private String parid;
    private String saletype;

    public String getParid() {
        return parid;
    }

    public void setParid(String parid) {
        this.parid = parid;
    }

    public String getSaletype() {
        return saletype;
    }

    public void setSaletype(String saletype) {
        this.saletype = saletype;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "parid='" + parid + '\'' +
                ", saletype='" + saletype + '\'' +
                '}';
    }
}