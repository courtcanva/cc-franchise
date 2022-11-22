package com.courtcanva.ccfranchise.exceptions;

import com.courtcanva.ccfranchise.dtos.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ResourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleResourceAlreadyExistException(ResourceAlreadyExistException e) {

        return ErrorDto.builder()
                .errorCode(HttpStatus.CONFLICT.value())
                .details(e.getMessage())
                .build();

    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleResourceNotFoundException(ResourceNotFoundException e) {

        return ErrorDto.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
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

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleConstraintViolationException(ConstraintViolationException e) {

        log.debug("Method argument is not valid", e);

        return ErrorDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .details(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MailingClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleMailingClientException(MailingClientException ex) {
        log.debug(ex.getMessage());
        return ErrorDto.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = ExpiredVerificationTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDto handleExpiredVerificationTokenException(ExpiredVerificationTokenException ex) {
        log.debug(ex.getMessage());
        return ErrorDto.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .details(ex.getMessage())
                .build();
    }
    @ExceptionHandler(value = PageNumberNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handlePageNumberNotValidException(PageNumberNotValidException ex) {

        return ErrorDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
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
