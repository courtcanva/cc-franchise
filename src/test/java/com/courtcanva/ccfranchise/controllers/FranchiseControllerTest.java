package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.services.FranchiseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FranchiseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("can sign up new franchise")
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


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(franchiseDto));


        mockMvc.perform(requestBuilder).andExpect(status().isOk());


    }

}