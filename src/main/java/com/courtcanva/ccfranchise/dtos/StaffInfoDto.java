package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.constants.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffInfoDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String franchiseAbn;

    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^[0-9]{9}｜0[0-9]{9}$")
    private String phoneNumber;

    @NotNull
    private String residentialAddress;

    @NotNull
    private int postcode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private State state;

    @NotNull
    @Pattern(regexp = ".*[0-9]+.*[A-Z]+.*|.*[A-Z]+.*[0-9]+.*")
    private String password;


}
