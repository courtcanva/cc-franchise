package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.ErrorDto;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleResourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest) {

        return ErrorDto.builder()
                .details(webRequest.getDescription(false))
                .message(e.getMessage())
                .timestamp(new Date())
                .build();
    }
}
