package com.data.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ValidationErrorHandler {
    private MessageSource messageSource;

    @Autowired
    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        FieldError fieldError = fieldErrors.get(0);
        String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);

        return new Error(fieldError.getField(), localizedErrorMessage);
    }

//    private ValidationError processFieldErrors(List<FieldError> fieldErrors) {
//        ValidationError error = new ValidationError();
//
//        for (FieldError fieldError: fieldErrors) {
//            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
//            error.addFieldError(fieldError.getField(), localizedErrorMessage);
//        }
//
//        return error;
//    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (!localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}
