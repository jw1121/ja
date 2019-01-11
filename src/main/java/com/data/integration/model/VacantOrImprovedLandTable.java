package com.data.integration.model;

import java.util.List;

public class VacantOrImprovedLandTable {

    private boolean inverted;
    private List<String> headers = null;
    private List<List<String>> values = null;

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> getValues() {
        return values;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }

}
