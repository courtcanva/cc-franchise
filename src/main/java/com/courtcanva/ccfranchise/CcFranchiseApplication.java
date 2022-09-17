package com.courtcanva.ccfranchise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class CcFranchiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcFranchiseApplication.class, args);
    }
}
