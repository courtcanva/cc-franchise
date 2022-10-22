package com.courtcanva.ccfranchise.exceptions;

import com.courtcanva.ccfranchise.dtos.OpenOrderResponseDto;
import java.util.List;

public class OrderStatusInvalidException extends RuntimeException {
    public OrderStatusInvalidException(String message) {
        super(message);
    }
}
