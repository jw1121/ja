package com.data.integration.model;

import java.io.Serializable;
import java.util.List;

public class Cama implements Serializable {
    private final static long serialVersionUID = -189947844355325901L;

    private int book;
    private int page;
    private double stampval;
    private int price;
    private String saledt;
    private String recorddt;
    private String instrtyp;
    private int nopar;
    private String source;
    private Object steb;
    private int taxyr;
    private List<Parcel> parcels = null;
    private Mailing_Address mailing_Address;
    private List<Owner> owners = null;

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public double getStampval() {
        return stampval;
    }

    public void setStampval(double stampval) {
        this.stampval = stampval;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSaledt() {
        return saledt;
    }

    public void setSaledt(String saledt) {
        this.saledt = saledt;
    }

    public String getRecorddt() {
        return recorddt;
    }

    public void setRecorddt(String recorddt) {
        this.recorddt = recorddt;
    }

    public String getInstrtyp() {
        return instrtyp;
    }

    public void setInstrtyp(String instrtyp) {
        this.instrtyp = instrtyp;
    }

    public int getNopar() {
        return nopar;
    }

    public void setNopar(int nopar) {
        this.nopar = nopar;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Object getSteb() {
        return steb;
    }

    public void setSteb(Object steb) {
        this.steb = steb;
    }

    public int getTaxyr() {
        return taxyr;
    }

    public void setTaxyr(int taxyr) {
        this.taxyr = taxyr;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Mailing_Address getMailing_Address() {
        return mailing_Address;
    }

    public void setMailing_Address(Mailing_Address mailing_Address) {
        this.mailing_Address = mailing_Address;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Cama{" +
                "book=" + book +
                ", page=" + page +
                ", stampval=" + stampval +
                ", price=" + price +
                ", saledt='" + saledt + '\'' +
                ", recorddt='" + recorddt + '\'' +
                ", instrtyp='" + instrtyp + '\'' +
                ", nopar=" + nopar +
                ", source='" + source + '\'' +
                ", steb=" + steb +
                ", taxyr=" + taxyr +
                ", parcels=" + parcels +
                ", mailing_address=" + mailing_Address +
                ", owners=" + owners +
                '}';
    }
}
