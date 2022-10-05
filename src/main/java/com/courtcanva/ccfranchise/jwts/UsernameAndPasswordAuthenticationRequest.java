package com.courtcanva.ccfranchise.jwts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

}
