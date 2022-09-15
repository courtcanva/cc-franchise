package com.courtcanva.ccfranchise.service;

import com.courtcanva.ccfranchise.dto.RequiredEmail;
import com.courtcanva.ccfranchise.dto.Result;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignUpServiceTest {
    @Autowired
    private SignUpService signUpService;

    Logger logger = LoggerFactory.getLogger(SignUpServiceTest.class);

    @Test
    void verifyEmail() {
        RequiredEmail requiredEmail = new RequiredEmail("222gmail.com");
        Result result = signUpService.verifyEmail(requiredEmail);
        logger.debug(result.getMsg());
    }
}