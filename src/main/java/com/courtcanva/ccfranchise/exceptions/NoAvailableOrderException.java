package com.courtcanva.ccfranchise.exceptions;

public class NoAvailableOrderException extends RuntimeException {

    public NoAvailableOrderException(String message) {
        super(message);
    }
}
