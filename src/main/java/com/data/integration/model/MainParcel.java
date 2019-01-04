package com.data.integration.model;

import java.io.Serializable;
import java.util.List;

public class MainParcel implements Serializable
{

    private int id;
    private String legalDescription;
    private int matchScore;
    private List<String> ownerNames = null;
    private List<String> ownerTypes = null;
    private String parcelNumber;
    private String sourceId;
    private final static long serialVersionUID = 7352920892515435802L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLegalDescription() {
        return legalDescription;
    }

    public void setLegalDescription(String legalDescription) {
        this.legalDescription = legalDescription;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public List<String> getOwnerNames() {
        return ownerNames;
    }

    public void setOwnerNames(List<String> ownerNames) {
        this.ownerNames = ownerNames;
    }

    public List<String> getOwnerTypes() {
        return ownerTypes;
    }

    public void setOwnerTypes(List<String> ownerTypes) {
        this.ownerTypes = ownerTypes;
    }

    public String getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber) {
        this.parcelNumber = parcelNumber;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

}