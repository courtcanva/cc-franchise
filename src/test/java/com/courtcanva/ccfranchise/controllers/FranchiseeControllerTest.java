package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.FranchiseeDto;

import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import com.courtcanva.ccfranchise.services.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FranchiseeControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Mock
    private StaffService staffService;

    @Mock
    private FranchiseeService franchiseService;

    @InjectMocks
    private FranchiseeController franchiseController;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(franchiseController).build();

    }

    @Test
    void shouldReturnStaffAndFranchiseId() throws Exception {

        FranchiseeDto franchiseDto = FranchiseeDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .ABN(1231232)
                .build();
        StaffDto staffDto = StaffDto.builder()
                .address("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state("NSW")
                .postcode(3213)
                .password("fdsafsdfdsaf")
                .phoneNumber("31232131")
                .firstName("first")
                .lastName("last")
                .build();
        franchiseDto.setStaff(staffDto);
        when(staffService.createStaffOfFranchisee(any())).thenReturn(12L);
        when(franchiseService.createFranchisee(any())).thenReturn(21L);


        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                        .content(new ObjectMapper().writeValueAsString(franchiseDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }
}