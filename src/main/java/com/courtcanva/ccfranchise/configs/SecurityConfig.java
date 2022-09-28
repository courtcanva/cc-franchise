package com.courtcanva.ccfranchise.configs;

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


    private List<String> allowedOrigins;

    private List<String> allowedMethods;

    private List<String> allowedHeaders;

    private final SecretKey secretKey;

    private final StaffDetailService staffDetailService;

    private final JwtConfig jwtConfig;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedMethods(allowedOrigins);
                    cors.setAllowedOrigins(allowedMethods);
                    cors.setAllowedHeaders(allowedHeaders);
                    return cors;
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authorize ->
                        authorize.antMatchers("/franchisee/signup").permitAll()
                                .antMatchers("/staff/login").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilter(
                        new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),
                                secretKey,
                                jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);

    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(staffDetailService);
        return new ProviderManager(provider);
    }
}
