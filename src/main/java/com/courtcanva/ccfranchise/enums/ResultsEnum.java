package com.courtcanva.ccfranchise.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultsEnum {
    SUCCESS(200, "success"),
    EMAIL_EXISTS(10001,"The email is already exists");


    private Integer code;

    private String message;

    ResultsEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
