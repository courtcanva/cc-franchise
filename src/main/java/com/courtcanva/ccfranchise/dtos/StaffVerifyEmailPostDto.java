package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StaffVerifyEmailPostDto {

    @NotBlank(message = "Token is mandatory")
    private String token;

    @NotBlank(message = "Email is mandatory")
    private String email;

}
