package com.data.integration;

public class CamaException extends Exception {
    CamaException(String msg) {
        super(msg);
    }

    CamaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
