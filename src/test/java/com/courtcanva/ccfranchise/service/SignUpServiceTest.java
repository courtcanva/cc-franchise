package com.courtcanva.ccfranchise.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SignUpServiceTest {
    @Autowired
    private SignUpService signUpService;

    Logger logger = LoggerFactory.getLogger(SignUpServiceTest.class);

    @Test
    void verifyEmail() {
        Boolean isExisted1 = signUpService.verifyEmail("222@gmail.com");
        Boolean isExisted2 = signUpService.verifyEmail("2222@gmail.com");
        Assertions.assertEquals(true, isExisted1);
        Assertions.assertEquals(false, isExisted2);
    }
}