package com.courtcanva.ccfranchise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorDto {
    private String message;
    private List<String> details;
}
