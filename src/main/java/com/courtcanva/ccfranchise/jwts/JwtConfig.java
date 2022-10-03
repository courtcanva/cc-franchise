package com.courtcanva.ccfranchise.jwts;


import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;


@Configuration
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secretKey;

    public String getAuthorization() {
        return HttpHeaders.AUTHORIZATION;
    }

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
