package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffVerifyEmailPostDto {
    @NotBlank(message = "Staff ID is mandatory")
    private String id;

    @NotBlank(message = "Verification token is mandatory")
    private String verificationToken;
}
