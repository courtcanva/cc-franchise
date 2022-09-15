package com.courtcanva.ccfranchise.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data;
}
