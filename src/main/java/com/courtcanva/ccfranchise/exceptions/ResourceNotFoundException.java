package com.courtcanva.ccfranchise.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final String fileValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fileValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fileValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fileValue = fileValue;
    }

}