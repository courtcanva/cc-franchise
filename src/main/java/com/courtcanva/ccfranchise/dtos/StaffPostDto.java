package com.courtcanva.ccfranchise.dtos;

import com.courtcanva.ccfranchise.constants.AUState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffPostDto {

    @NotBlank(message = "Staff's firstName is mandatory")
    private String firstName;

    @NotBlank(message = "Staff's lastName is mandatory")
    private String lastName;

    @NotBlank(message = "Staff's email is mandatory")
    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Staff's phone number is mandatory")
    @Pattern(regexp = "^0[0-9]{9}|[0-9]{9}$",message = "Incorrect phone number format")
    private String phoneNumber;

    @NotBlank(message = "Staff's residential address is mandatory")
    private String residentialAddress;

    @NotNull(message = "Staff's postcode is mandatory")
    private int postcode;

    @NotNull(message = "Staff's state is mandatory")
    @Enumerated(EnumType.STRING)
    private AUState state;

    @NotBlank(message = "Staff's password is mandatory")
    @Pattern(regexp = ".*[0-9]+.*[A-Z]+.*|.*[A-Z]+.*[0-9]+.*",message = "At least one uppercase letter and one digit")
    private String password;


}
