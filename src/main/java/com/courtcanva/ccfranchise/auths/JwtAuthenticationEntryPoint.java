package com.courtcanva.ccfranchise.auths;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.debug(authException.toString());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().print("username or password is wrong");
//        log.debug("username and password is not authenticated");
        log.debug(authException.getMessage());
    }
}
