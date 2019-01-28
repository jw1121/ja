package com.data.integration.validation;

import java.util.HashMap;

final public class Constant {

    public static final String nullFieldCode;
    public static final String nullFieldMessage;
    public static final String invalidTypeCode;
    public static final String invalidTypeMessage;
    public static final String invalidLengthCode;
    public static final String invalidLengthMessage;
    public static final String ParcelNotFoundCode;
    public static final String ParcelNotFoundMessage;
    public static final String InvalidDateCode;
    public static final String InvalidDateMessage;
    public static final String UnknownErrorCode;
    public static final String InvalidValueCode;
    public static final String InvalidValueMessage;

    public static HashMap<String, String> message;

    static {
        nullFieldCode = "40001";
        nullFieldMessage = "Non-null check failed: {}";
        invalidTypeCode = "40002";
        invalidTypeMessage = "Type check failed: {}";
        invalidLengthCode = "40003";
        invalidLengthMessage = "Character length check failed: {}";
        ParcelNotFoundCode = "40004";
        ParcelNotFoundMessage = "Parcel number not found: {}";
        InvalidDateCode = "40005";
        InvalidDateMessage = "Date check failed: {}";
        InvalidValueCode = "40006";
        InvalidValueMessage = "Value check failed: {}";
        UnknownErrorCode = "49999";

        message = new HashMap<>();
        message.put(nullFieldCode, nullFieldMessage);
        message.put(invalidLengthCode, invalidLengthMessage);
        message.put(invalidTypeCode, invalidTypeMessage);
        message.put(ParcelNotFoundCode, ParcelNotFoundMessage);
        message.put(InvalidValueCode, InvalidValueMessage);
        message.put(InvalidDateCode, InvalidDateMessage);
    }
}
