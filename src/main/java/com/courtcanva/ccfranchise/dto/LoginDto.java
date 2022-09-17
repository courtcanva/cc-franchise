package com.courtcanva.ccfranchise.dto;


import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
@Data
public class LoginDto {
    @Pattern(regexp = "[a-zA-Z0-9_]+@\\w+(\\.com|\\.cn){1}", message = "Email format invalid")
    @NonNull
    private String email;

    /*@Pattern(
            regexp = "",
            message = "Password format invalid")*/
    @NonNull
    private String password;
}
