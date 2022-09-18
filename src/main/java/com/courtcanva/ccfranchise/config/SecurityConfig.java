package com.courtcanva.ccfranchise.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Setter
@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "management.endpoints.web.cors")
public class SecurityConfig {


    private List<String> allowedOrigins;

    private List<String> allowedMethods;

    private List<String> allowedHeaders;

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
                .authorizeHttpRequests().antMatchers("/*").permitAll();
        return http.build();
    }
}
