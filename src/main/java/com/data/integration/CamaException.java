package com.data.integration;

public class CamaException extends RuntimeException {
    private String code;

    CamaException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    CamaException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
