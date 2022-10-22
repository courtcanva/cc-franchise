package com.courtcanva.ccfranchise.exceptions;

public class OrderStatusInvalidException extends RuntimeException {
    public OrderStatusInvalidException(String message) {
        super(message);
    }
}
