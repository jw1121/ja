package com.data.integration.model;

import com.data.integration.utility.StringConvert;

import java.io.Serializable;
import java.util.List;

public class VacantOrImprovedLandTable implements Serializable {
    private final static long serialVersionUID = 1628458930270201928L;

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
        this.headers = StringConvert.toUpper(headers);
    }

    public List<List<String>> getValues() {
        return values;
    }

    public void setValues(List<List<String>> values) {
        this.values = StringConvert.listToUpper(values);
    }

    @Override
    public String toString() {
        String header = "";
        for(String head : headers) {
            header += head + " ";
        }

        String kv = "";
        for(List<String> value : values) {
            for(String head : value) {
                kv += head + " ";
            }
            kv += "; ";
        }
        return "VacantOrImprovedLandTable{" +
                "inverted=" + inverted +
                ", headers=" + header +
                ", values=" + kv +
                '}';
    }
}
