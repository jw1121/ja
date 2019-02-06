package com.data.integration.model;

import java.io.Serializable;
import java.util.List;

public class ParcelMatchCardsComponent implements Serializable {
    private final static long serialVersionUID = -3144150903519337246L;

    private List<MainParcel> mainParcels = null;
    private List<Object> potentialMatchParcels = null;

    public List<MainParcel> getMainParcels() {
        return mainParcels;
    }

    public void setMainParcels(List<MainParcel> mainParcels) {
        this.mainParcels = mainParcels;
    }

    public List<Object> getPotentialMatchParcels() {
        return potentialMatchParcels;
    }

    public void setPotentialMatchParcels(List<Object> potentialMatchParcels) {
        this.potentialMatchParcels = potentialMatchParcels;
    }

    @Override
    public String toString() {
        return "ParcelMatchCardsComponent{" +
                "mainParcels=" + mainParcels +
                ", potentialMatchParcels=" + potentialMatchParcels +
                '}';
    }
}