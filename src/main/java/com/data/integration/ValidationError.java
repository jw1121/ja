package com.data.integration;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    private List<Error> fieldErrors = new ArrayList<>();

    public void addFieldError(String path, String message) {
        Error error = new Error(path, message);
        fieldErrors.add(error);
    }

    public List<Error> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<Error> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
