package com.courtcanva.ccfranchise.jwts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

}
