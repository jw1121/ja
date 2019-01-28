package com.data.integration.validation;

import com.data.integration.CamaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CamaExceptionHadler {

    @ExceptionHandler(CamaException.class)
    public final ResponseEntity<Object> CamaExceptionHandler(CamaException camaException, WebRequest request) {
        Error error = new Error(camaException.getCode(), camaException.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> RuntimeExceptionHandler(RuntimeException runtimeException, WebRequest request) {
        Error error = new Error(Constant.UnknownErrorCode, runtimeException.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
