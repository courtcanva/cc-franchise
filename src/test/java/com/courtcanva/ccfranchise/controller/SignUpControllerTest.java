package com.courtcanva.ccfranchise.controller;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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

    Logger logger = LoggerFactory.getLogger(SignUpControllerTest.class);

    @Test
    void verifyEmail() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        String param = "222@gmail.com";
        RequestBuilder request = MockMvcRequestBuilders.post("/signUp/verifyEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        MvcResult mvcResult = mock.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }
}