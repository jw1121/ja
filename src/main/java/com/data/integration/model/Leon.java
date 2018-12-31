package com.data.integration.model;

import java.io.Serializable;

public class Leon implements Serializable {

    private UserData userData;
    private SaleData saleData;
    private final static long serialVersionUID = 1808490387764442238L;

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