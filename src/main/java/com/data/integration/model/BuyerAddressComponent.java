package com.data.integration.model;

import com.data.integration.utility.StringConvert;

import java.io.Serializable;

public class BuyerAddressComponent implements Serializable
{

    private String buyerAddressCareOf;
    private String addressCategory;
    private int buyerAddressStreetNumber1;
    private String buyerAddressStreetNumber2;
    private String buyerAddressStreetDirectionalPrefix;
    private String buyerAddressStreetName;
    private String buyerAddressStreetSuffix;
    private String buyerAddressStreetDirectionalSuffix;
    private String buyerCity;
    private String buyerState;
    private String buyerCountry;
    private String buyerForeignPostalCode;
    private String buyerAddressSecondaryUnitDesignator;
    private String buyerAddressSecondaryUnitNumber;
    private String buyerAddress1;
    private String buyerAddress2;
    private String buyerAddress3;
    private String buyerZip;
    private String buyerZip4;
    private String buyerMailingNotificationCode;
    private final static long serialVersionUID = 6717336087675580297L;

    public String getBuyerAddressCareOf() {
        return buyerAddressCareOf;
    }

    public void setBuyerAddressCareOf(String buyerAddressCareOf) {
        this.buyerAddressCareOf = StringConvert.toUpper(buyerAddressCareOf);
    }

    public String getAddressCategory() {
        return addressCategory;
    }

    public void setAddressCategory(String addressCategory) {
        this.addressCategory = StringConvert.toUpper(addressCategory);
    }

    public int getBuyerAddressStreetNumber1() {
        return buyerAddressStreetNumber1;
    }

    public void setBuyerAddressStreetNumber1(int buyerAddressStreetNumber1) {
        this.buyerAddressStreetNumber1 = buyerAddressStreetNumber1;
    }

    public String getBuyerAddressStreetNumber2() {
        return buyerAddressStreetNumber2;
    }

    public void setBuyerAddressStreetNumber2(String buyerAddressStreetNumber2) {
        this.buyerAddressStreetNumber2 = StringConvert.toUpper(buyerAddressStreetNumber2);
    }

    public String getBuyerAddressStreetDirectionalPrefix() {
        return buyerAddressStreetDirectionalPrefix;
    }

    public void setBuyerAddressStreetDirectionalPrefix(String buyerAddressStreetDirectionalPrefix) {
        this.buyerAddressStreetDirectionalPrefix = StringConvert.toUpper(buyerAddressStreetDirectionalPrefix);
    }

    public String getBuyerAddressStreetName() {
        return buyerAddressStreetName;
    }

    public void setBuyerAddressStreetName(String buyerAddressStreetName) {
        this.buyerAddressStreetName = StringConvert.toUpper(buyerAddressStreetName);
    }

    public String getBuyerAddressStreetSuffix() {
        return buyerAddressStreetSuffix;
    }

    public void setBuyerAddressStreetSuffix(String buyerAddressStreetSuffix) {
        this.buyerAddressStreetSuffix = StringConvert.toUpper(buyerAddressStreetSuffix);
    }

    public String getBuyerAddressStreetDirectionalSuffix() {
        return buyerAddressStreetDirectionalSuffix;
    }

    public void setBuyerAddressStreetDirectionalSuffix(String buyerAddressStreetDirectionalSuffix) {
        this.buyerAddressStreetDirectionalSuffix = StringConvert.toUpper(buyerAddressStreetDirectionalSuffix);
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = StringConvert.toUpper(buyerCity);
    }

    public String getBuyerState() {
        return buyerState;
    }

    public void setBuyerState(String buyerState) {
        this.buyerState = StringConvert.toUpper(buyerState);
    }

    public String getBuyerCountry() {
        return buyerCountry;
    }

    public void setBuyerCountry(String buyerCountry) {
        this.buyerCountry = StringConvert.toUpper(buyerCountry);
    }

    public String getBuyerForeignPostalCode() {
        return buyerForeignPostalCode;
    }

    public void setBuyerForeignPostalCode(String buyerForeignPostalCode) {
        this.buyerForeignPostalCode = StringConvert.toUpper(buyerForeignPostalCode);
    }

    public String getBuyerAddressSecondaryUnitDesignator() {
        return buyerAddressSecondaryUnitDesignator;
    }

    public void setBuyerAddressSecondaryUnitDesignator(String buyerAddressSecondaryUnitDesignator) {
        this.buyerAddressSecondaryUnitDesignator = StringConvert.toUpper(buyerAddressSecondaryUnitDesignator);
    }

    public String getBuyerAddressSecondaryUnitNumber() {
        return buyerAddressSecondaryUnitNumber;
    }

    public void setBuyerAddressSecondaryUnitNumber(String buyerAddressSecondaryUnitNumber) {
        this.buyerAddressSecondaryUnitNumber = StringConvert.toUpper(buyerAddressSecondaryUnitNumber);
    }

    public String getBuyerAddress1() {
        return buyerAddress1;
    }

    public void setBuyerAddress1(String buyerAddress1) {
        this.buyerAddress1 = StringConvert.toUpper(buyerAddress1);
    }

    public String getBuyerAddress2() {
        return buyerAddress2;
    }

    public void setBuyerAddress2(String buyerAddress2) {
        this.buyerAddress2 = StringConvert.toUpper(buyerAddress2);
    }

    public String getBuyerAddress3() {
        return buyerAddress3;
    }

    public void setBuyerAddress3(String buyerAddress3) {
        this.buyerAddress3 = StringConvert.toUpper(buyerAddress3);
    }

    public String getBuyerZip() {
        return buyerZip;
    }

    public void setBuyerZip(String buyerZip) {
        this.buyerZip = StringConvert.toUpper(buyerZip);
    }

    public String getBuyerZip4() {
        return buyerZip4;
    }

    public void setBuyerZip4(String buyerZip4) {
        this.buyerZip4 = StringConvert.toUpper(buyerZip4);
    }

    public String getBuyerMailingNotificationCode() {
        return buyerMailingNotificationCode;
    }

    public void setBuyerMailingNotificationCode(String buyerMailingNotificationCode) {
        this.buyerMailingNotificationCode = StringConvert.toUpper(buyerMailingNotificationCode);
    }

    @Override
    public String toString() {
        return "BuyerAddressComponent{" +
                "buyerAddressCareOf='" + buyerAddressCareOf + '\'' +
                ", addressCategory='" + addressCategory + '\'' +
                ", buyerAddressStreetNumber1=" + buyerAddressStreetNumber1 +
                ", buyerAddressStreetNumber2='" + buyerAddressStreetNumber2 + '\'' +
                ", buyerAddressStreetDirectionalPrefix='" + buyerAddressStreetDirectionalPrefix + '\'' +
                ", buyerAddressStreetName='" + buyerAddressStreetName + '\'' +
                ", buyerAddressStreetSuffix='" + buyerAddressStreetSuffix + '\'' +
                ", buyerAddressStreetDirectionalSuffix='" + buyerAddressStreetDirectionalSuffix + '\'' +
                ", buyerCity='" + buyerCity + '\'' +
                ", buyerState='" + buyerState + '\'' +
                ", buyerCountry='" + buyerCountry + '\'' +
                ", buyerForeignPostalCode='" + buyerForeignPostalCode + '\'' +
                ", buyerAddressSecondaryUnitDesignator='" + buyerAddressSecondaryUnitDesignator + '\'' +
                ", buyerAddressSecondaryUnitNumber='" + buyerAddressSecondaryUnitNumber + '\'' +
                ", buyerAddress1='" + buyerAddress1 + '\'' +
                ", buyerAddress2='" + buyerAddress2 + '\'' +
                ", buyerAddress3='" + buyerAddress3 + '\'' +
                ", buyerZip='" + buyerZip + '\'' +
                ", buyerZip4='" + buyerZip4 + '\'' +
                ", buyerMailingNotificationCode='" + buyerMailingNotificationCode + '\'' +
                '}';
    }
}