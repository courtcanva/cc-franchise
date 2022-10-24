package com.courtcanva.ccfranchise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CcFranchiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcFranchiseApplication.class, args);
    }
}
