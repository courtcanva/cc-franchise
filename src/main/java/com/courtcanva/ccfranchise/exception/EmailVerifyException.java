package com.courtcanva.ccfranchise.exception;


public class EmailVerifyException extends Exception {
    private String msg;

    public EmailVerifyException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "msg";
    }
}
