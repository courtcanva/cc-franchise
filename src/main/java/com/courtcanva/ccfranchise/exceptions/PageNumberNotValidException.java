package com.courtcanva.ccfranchise.exceptions;

public class PageNumberNotValidException extends RuntimeException{
    public PageNumberNotValidException(String message) {
        super(message);
    }
}
