package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FranchiseControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc  = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }
    @Test
    public void shouldReturnOkWhenCreateNewFranchise() throws Exception {

        StaffDto staffDto = StaffDto.builder()
                .address("zetland")
                .email("courtcanva@gmail.com")
                .state("NSW")
                .lastName("Will")
                .firstName("Jerry")
                .phoneNumber("1232132")
                .password("123456")
                .verificationLink("url://12321312321")
                .postcode(2017)
                .build();

        FranchiseDto franchiseDto = FranchiseDto.builder()
                .staff(staffDto)
                .ABN("1112312")
                .businessAddress("nsw zetland")
                .businessName("CCCCVVVVV")
                .build();


        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(franchiseDto)))
                .andExpect(status().isOk());


    }

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/hello")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testHell1o() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/hello")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )

                .andExpect(status().isOk());
    }

}