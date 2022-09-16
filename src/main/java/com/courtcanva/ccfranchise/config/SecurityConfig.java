package com.courtcanva.ccfranchise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "management.endpoints.web.cors")
public class SecurityConfig {
    @Value("${allowed-origins}")
    private List<String> origins;

    @Value("${allowed-methods}")
    private List<String> methods;

    @Value("${allowed-headers}")
    private List<String> headers;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedMethods(methods);
                    cors.setAllowedOrigins(origins);
                    cors.setAllowedHeaders(headers);
                    return cors;
                })
                .and()
                .authorizeHttpRequests().antMatchers("/*").permitAll();
        return http.build();
    }
}

