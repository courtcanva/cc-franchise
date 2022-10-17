package com.courtcanva.ccfranchise.jwts;

import com.courtcanva.ccfranchise.auths.StaffDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String LOGIN_URL = "/staff/signin";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORITIES = "authorities";
    private static final String STAFF_ID = "StaffId";

    public static final  String FRANCHISEE_ID = "FranchiseeId";
    private final AuthenticationManager authenticationManager;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, SecretKey secretKey, JwtConfig jwtConfig) {

        super.setFilterProcessesUrl(LOGIN_URL);
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;

    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );

        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim(AUTHORITIES, authResult.getAuthorities())
                .claim(STAFF_ID, ((StaffDetail) authResult.getPrincipal()).getId())
                .claim(FRANCHISEE_ID,((StaffDetail) authResult.getPrincipal()).getFranchiseeId())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(secretKey)
                .compact();

        response.addHeader(jwtConfig.getAuthorization(), BEARER + token);

    }
}
