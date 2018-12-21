package com.data.integration.model;

import javax.validation.constraints.Max;
import java.io.Serializable;

public class Mailing_Address implements Serializable {
    private final static long serialVersionUID = -3264487308538166978L;

    @Max(60)
    private String careof;
    @Max(1)
    private String addrtype;
    @Max(10)
    private int adrno;
    @Max(6)
    private String adradd;
    @Max(2)
    private String adrdir;
    @Max(30)
    private String adrstr;
    @Max(8)
    private String adrsuf;
    @Max(8)
    private String adrsuf2;
    @Max(40)
    private String cityname;
    @Max(2)
    private String statecode;
    @Max(30)
    private String country;
    @Max(10)
    private String postalcode;
    @Max(10)
    private String unitdesc;
    @Max(10)
    private String unitno;
    @Max(80)
    private String addr1;
    @Max(80)
    private String addr2;
    @Max(80)
    private String addr3;
    @Max(80)
    private String zip1;
    @Max(80)
    private String zip2;
    @Max(20)
    private String user4;

    public String getCareof() {
        return careof;
    }

    public void setCareof(String careof) {
        this.careof = careof;
    }

    public String getAddrtype() {
        return addrtype;
    }

    public void setAddrtype(String addrtype) {
        this.addrtype = addrtype;
    }

    public int getAdrno() {
        return adrno;
    }

    public void setAdrno(int adrno) {
        this.adrno = adrno;
    }

    public String getAdradd() { return adradd; }

    public void setAdradd(String adradd) {
        this.adradd = adradd;
    }

    public String getAdrdir() {
        return adrdir;
    }

    public void setAdrdir(String adrdir) {
        this.adrdir = adrdir;
    }

    public String getAdrstr() {
        return adrstr;
    }

    public void setAdrstr(String adrstr) {
        this.adrstr = adrstr;
    }

    public String getAdrsuf() {
        return adrsuf;
    }

    public void setAdrsuf(String adrsuf) {
        this.adrsuf = adrsuf;
    }

    public String getAdrsuf2() {
        return adrsuf2;
    }

    public void setAdrsuf2(String adrsuf2) {
        this.adrsuf2 = adrsuf2;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getCountry() { return country; }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() { return postalcode; }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getUnitdesc() {
        return unitdesc;
    }

    public void setUnitdesc(String unitdesc) {
        this.unitdesc = unitdesc;
    }

    public String getUnitno() {
        return unitno;
    }

    public void setUnitno(String unitno) {
        this.unitno = unitno;
    }

    public String getAddr1() { return addr1; }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getAddr3() {
        return addr3;
    }

    public void setAddr3(String addr3) {
        this.addr3 = addr3;
    }

    public String getZip1() {
        return zip1;
    }

    public void setZip1(String zip1) {
        this.zip1 = zip1;
    }

    public String getZip2() {
        return zip2;
    }

    public void setZip2(String zip2) {
        this.zip2 = zip2;
    }

    public String getUser4() {
        return user4;
    }

    public void setUser4(String user4) {
        this.user4 = user4;
    }

    @Override
    public String toString() {
        return "MailingAddress{" +
                "careof='" + careof + '\'' +
                ", addrtype='" + addrtype + '\'' +
                ", adrno=" + adrno +
                ", adradd=" + adradd +
                ", adrdir='" + adrdir + '\'' +
                ", adrstr='" + adrstr + '\'' +
                ", adrsuf='" + adrsuf + '\'' +
                ", adrsuf2='" + adrsuf2 + '\'' +
                ", cityname='" + cityname + '\'' +
                ", statecode='" + statecode + '\'' +
                ", country=" + country +
                ", postalcode=" + postalcode +
                ", unitdesc='" + unitdesc + '\'' +
                ", unitno='" + unitno + '\'' +
                ", addr1=" + addr1 +
                ", addr2=" + addr2 +
                ", addr3=" + addr3 +
                ", zip1='" + zip1 + '\'' +
                ", zip2='" + zip2 + '\'' +
                ", user4='" + user4 + '\'' +
                '}';
    }
}