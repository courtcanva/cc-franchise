package com.courtcanva.ccfranchise.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class RequiredEmail {
    @NonNull
    private String email;
}
