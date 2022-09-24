package com.courtcanva.ccfranchise.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @NotBlank(message = "Staff's email is mandatory")
    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Staff's password is mandatory")
    @Pattern(regexp = ".*[0-9]+.*[A-Z]+.*|.*[A-Z]+.*[0-9]+.*", message = "At least one uppercase letter and one digit")
    private String password;


}
