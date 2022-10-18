package com.courtcanva.ccfranchise.jwts;

import com.courtcanva.ccfranchise.auths.FranchiseeAuthenticationToken;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private static final String AUTHORITY = "authority";
    private static final String AUTHORITIES = "authorities";

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Override
    @SneakyThrows

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorization());

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace(BEARER, "");

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String username = body.getSubject();
        Long franchiseeId = ((Number) body.get("FranchiseeId")).longValue();

        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get(AUTHORITIES);

        Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(map -> new SimpleGrantedAuthority(map.get(AUTHORITY)))
                .collect(Collectors.toSet());

        Authentication authentication = new FranchiseeAuthenticationToken(
                franchiseeId,
                username,
                null,
                grantedAuthorities

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
