package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private Integer postcode;

    @NotNull
    private String state;

    @NotNull
    private String password;

    @NotNull
    private String verificationLink;

}
