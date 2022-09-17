package com.courtcanva.ccfranchise.exception;


import com.courtcanva.ccfranchise.constant.EmailEnum;

public class EmailVerifyException extends RuntimeException {
    public EmailVerifyException() {
        super("The email is already existed");
    }
}
