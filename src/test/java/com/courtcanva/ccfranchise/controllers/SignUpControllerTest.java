package com.courtcanva.ccfranchise.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {
    @Resource
    private MockMvc mock;

    @Autowired
    private WebApplicationContext wac;

    @Test
    void verifyEmail() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request1 = MockMvcRequestBuilders.get("/signUp/checkEmail?email=222@gmail.com");
        RequestBuilder request2 = MockMvcRequestBuilders.get("/signUp/checkEmail?email=222gmail.com");
        mock.perform(request1)
                .andExpect(MockMvcResultMatchers.status().isOk());
        mock.perform(request2)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}