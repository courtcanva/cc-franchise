package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.constants.State;
import com.courtcanva.ccfranchise.dtos.FranchiseeInfoDto;
import com.courtcanva.ccfranchise.dtos.ResponseDto;
import com.courtcanva.ccfranchise.dtos.StaffInfoDto;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FranchiseeControllerTest {

    private MockMvc mockMvc;

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

        FranchiseeInfoDto franchiseDto = FranchiseeInfoDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .entityName("AAAA info")
                .state(State.ACT)
                .abn("1231232")
                .build();
        StaffInfoDto staffInfoDto = StaffInfoDto.builder()
                .residentialAddress("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state(State.ACT)
                .postcode(3213)
                .password("fdsafsdfdsaf")
                .phoneNumber("31232131")
                .firstName("first")
                .lastName("last")
                .build();
        franchiseDto.setStaff(staffInfoDto);

        ResponseDto mockResponse = ResponseDto.builder()
                .responseMessage("success")
                .responseCode("201")
                .build();
        when(franchiseService.createFranchiseeAndStaff(any())).thenReturn(mockResponse);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/signup")
                        .content(new ObjectMapper().writeValueAsString(franchiseDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        ResponseDto resultResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), ResponseDto.class);
        assertEquals("success", resultResponse.getResponseMessage());
        assertEquals("201", resultResponse.getResponseCode());

    }
}