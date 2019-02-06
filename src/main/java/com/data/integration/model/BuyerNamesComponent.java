package com.data.integration.model;

import com.data.integration.utility.StringConvert;

import java.io.Serializable;

public class BuyerNamesComponent implements Serializable {
    private final static long serialVersionUID = 2200930421120281414L;

    private int id;
    private String fullName1;
    private String fullName2;
    private double buyerPercentage;
    private String buyerType;
    private String buyerType2;
    private String buyerType3;
    private String buyerType4;
    private String buyerMaritalStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName1() {
        return fullName1;
    }

    public void setFullName1(String fullName1) {
        this.fullName1 = StringConvert.toUpper(fullName1);
    }

    public String getFullName2() {
        return fullName2;
    }

    public void setFullName2(String fullName2) {
        this.fullName2 = StringConvert.toUpper(fullName2);
    }

    public double getBuyerPercentage() {
        return buyerPercentage;
    }

    public void setBuyerPercentage(double buyerPercentage) {
        this.buyerPercentage = buyerPercentage;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = StringConvert.toUpper(buyerType);
    }

    public String getBuyerType2() {
        return buyerType2;
    }

    public void setBuyerType2(String buyerType2) {
        this.buyerType2 = StringConvert.toUpper(buyerType2);
    }

    public String getBuyerType3() {
        return buyerType3;
    }

    public void setBuyerType3(String buyerType3) {
        this.buyerType3 = StringConvert.toUpper(buyerType3);
    }

    public String getBuyerType4() {
        return buyerType4;
    }

    public void setBuyerType4(String buyerType4) {
        this.buyerType4 = StringConvert.toUpper(buyerType4);
    }

    public String getBuyerMaritalStatus() {
        return buyerMaritalStatus;
    }

    public void setBuyerMaritalStatus(String buyerMaritalStatus) {
        this.buyerMaritalStatus = StringConvert.toUpper(buyerMaritalStatus);
    }

    @Override
    public String toString() {
        return "BuyerNamesComponent{" +
                "id=" + id +
                ", fullName1='" + fullName1 + '\'' +
                ", fullName2='" + fullName2 + '\'' +
                ", buyerPercentage=" + buyerPercentage +
                ", buyerType='" + buyerType + '\'' +
                ", buyerType2='" + buyerType2 + '\'' +
                ", buyerType3='" + buyerType3 + '\'' +
                ", buyerType4='" + buyerType4 + '\'' +
                ", buyerMaritalStatus='" + buyerMaritalStatus + '\'' +
                '}';
    }
}