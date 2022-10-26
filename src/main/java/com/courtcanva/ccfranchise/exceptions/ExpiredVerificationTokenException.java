package com.courtcanva.ccfranchise.exceptions;

public class ExpiredVerificationTokenException extends RuntimeException {
    public ExpiredVerificationTokenException(String msg) {
        super(msg);
    }
}
