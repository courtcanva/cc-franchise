package com.courtcanva.ccfranchise.jwts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

}
