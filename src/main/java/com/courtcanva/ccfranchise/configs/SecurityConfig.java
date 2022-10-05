package com.courtcanva.ccfranchise.configs;

import lombok.Setter;
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
                    cors.setAllowedMethods(allowedMethods);
                    cors.setAllowedOrigins(allowedOrigins);
                    cors.setAllowedHeaders(allowedHeaders);
                    return cors;
                })
                .and()
                .authorizeHttpRequests().antMatchers("/*").permitAll();
        return http.build();
    }
}
