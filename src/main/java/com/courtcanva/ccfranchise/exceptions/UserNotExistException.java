package com.courtcanva.ccfranchise.exceptions;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String msg) {
        super(msg);
    }

}