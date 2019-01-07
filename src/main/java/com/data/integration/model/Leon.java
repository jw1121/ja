package com.data.integration.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Leon implements Serializable {
    private final static long serialVersionUID = 1808490387764442238L;

    private UserData userData;
    @NotNull(message = "SaleData is required.")
    @Valid
    private SaleData saleData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public SaleData getSaleData() {
        return saleData;
    }

    public void setSaleData(SaleData saleData) {
        this.saleData = saleData;
    }

}