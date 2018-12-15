package com.data.integration.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Owner implements Serializable {
    private final static long serialVersionUID = 5656010980952910334L;

    @NotNull
    private int ownseq;
    private String own1;
    private Object own2;
    private double pctown;
    private String owntype1;
    private String owntype2;
    private String owntype3;
    private Object owntype4;
    private String hidename;
    private String marstat;

    public int getOwnseq() {
        return ownseq;
    }

    public void setOwnseq(int ownseq) {
        this.ownseq = ownseq;
    }

    public String getOwn1() {
        return own1;
    }

    public void setOwn1(String own1) {
        this.own1 = own1;
    }

    public Object getOwn2() {
        return own2;
    }

    public void setOwn2(Object own2) {
        this.own2 = own2;
    }

    public double getPctown() {
        return pctown;
    }

    public void setPctown(double pctown) {
        this.pctown = pctown;
    }

    public String getOwntype1() {
        return owntype1;
    }

    public void setOwntype1(String owntype1) {
        this.owntype1 = owntype1;
    }

    public String getOwntype2() {
        return owntype2;
    }

    public void setOwntype2(String owntype2) {
        this.owntype2 = owntype2;
    }

    public String getOwntype3() {
        return owntype3;
    }

    public void setOwntype3(String owntype3) {
        this.owntype3 = owntype3;
    }

    public Object getOwntype4() {
        return owntype4;
    }

    public void setOwntype4(Object owntype4) {
        this.owntype4 = owntype4;
    }

    public String getHidename() {
        return hidename;
    }

    public void setHidename(String hidename) {
        this.hidename = hidename;
    }

    public String getMarstat() {
        return marstat;
    }

    public void setMarstat(String marstat) {
        this.marstat = marstat;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownseq=" + ownseq +
                ", own1='" + own1 + '\'' +
                ", own2=" + own2 +
                ", pctown=" + pctown +
                ", owntype1='" + owntype1 + '\'' +
                ", owntype2='" + owntype2 + '\'' +
                ", owntype3='" + owntype3 + '\'' +
                ", owntype4=" + owntype4 +
                ", hidename='" + hidename + '\'' +
                ", marstat='" + marstat + '\'' +
                '}';
    }
}