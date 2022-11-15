package com.courtcanva.ccfranchise.configs;

import com.courtcanva.ccfranchise.auths.JwtAuthenticationEntryPoint;
import com.courtcanva.ccfranchise.auths.StaffDetailService;
import com.courtcanva.ccfranchise.jwts.JwtConfig;
import com.courtcanva.ccfranchise.jwts.JwtTokenVerifier;
import com.courtcanva.ccfranchise.jwts.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.crypto.SecretKey;
import java.util.List;

@Setter
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "management.endpoints.web.cors")
public class SecurityConfig {


    private static final String[] AUTH_URL_WHITELIST = {
            "/staff/emails/*",
            "/franchisee/signup",
            "/suburbs",
            "/actuator/health",
            "/staff/verify"
    };
    private final SecretKey secretKey;
    private final StaffDetailService staffDetailService;
    private final JwtConfig jwtConfig;
    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedMethods(allowedMethods);
                    cors.setAllowedOrigins(allowedOrigins);
                    cors.setAllowedHeaders(allowedHeaders);
                    cors.setExposedHeaders(exposedHeaders);
                    return cors;
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authorize ->
                        authorize.antMatchers(AUTH_URL_WHITELIST).permitAll()
                                .antMatchers("/franchisee/{franchiseeId:^[1-9]\\d*$}/**")
                                .access("@guard.checkFranchiseeAccess(authentication, #franchiseeId)")
                                .anyRequest().authenticated()
                )
                .addFilter(
                        new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),
                                secretKey,
                                jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);

    }

    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(staffDetailService);
        return new ProviderManager(provider);
    }
}
