package com.courtcanva.ccfranchise.enums;

public enum EmailEnum {
    SUCCESS(200, "success"),
    EMAIL_EXISTS(10001, "The email is already exists"),
    EMAIL_NOT_VALID(10002, "The email format is incorrect");


    private Integer code;

    private String message;

    EmailEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
