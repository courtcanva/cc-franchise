package com.courtcanva.ccfranchise.exceptions;

import com.courtcanva.ccfranchise.dtos.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ResourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleResourceAlreadyExistException(ResourceAlreadyExistException e) {

        return ErrorDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .details(e.getMessage())
                .build();

    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleResourceNotFoundException(ResourceNotFoundException e) {

        return ErrorDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .details(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        log.debug("Method argument is not valid", e);

        return ErrorDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .details(e.getMessage())
                .build();

    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNoSuchElementException(NoSuchElementException ex) {
        return ErrorDto.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .details(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = MailingClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleMailingClientException(MailingClientException ex) {
        log.error(ex.getMessage());
        return ErrorDto.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleAllException(Exception e) {

        log.error("error occurred", e);

        return ErrorDto.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(e.getMessage())
                .build();
    }
}
