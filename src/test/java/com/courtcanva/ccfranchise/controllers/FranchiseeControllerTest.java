package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListPostDto;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.FranchiseeAndStaffTestHelper;
import com.courtcanva.ccfranchise.utils.SuburbTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FranchiseeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FranchiseeRepository franchiseeRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private FranchiseeController franchiseeController;
    @Autowired
    private SuburbRepository suburbRepository;

    @BeforeEach
    public void clear() {

        staffRepository.deleteAll();

        franchiseeRepository.deleteAll();

    }

    @Test
    void shouldReturnStaffAndFranchise() throws Exception {

        FranchiseeAndStaffPostDto franchiseeAndStaffPostDto = FranchiseeAndStaffTestHelper.createFranchiseeAndStaffPostDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/signup")
                        .content(objectMapper.writeValueAsString(franchiseeAndStaffPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.staffGetDto.email").value("baoruoxi@163.com"))
                .andExpect(jsonPath("$.franchiseeGetDto.abn").value("12312123111"));

    }

    @Test
    @WithMockUser
    void shouldReturnSelectSuburbs() throws Exception {
        Long mockFranchiseeId = franchiseeController.signUpFranchiseeAndStaff(new FranchiseeAndStaffPostDto(new FranchiseePostDto("CourtCanva", "CourtCanva LTD", "12312123111", "23468290381", "Melbourne", AUState.VIC, 3000), new StaffPostDto("Taylor", "Swift", "taylor.s@gmail.com", "123456789", "abc st", 3000, AUState.VIC, "sdjkhsd"))).getFranchiseeGetDto().getFranchiseeId();
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());

        SuburbListPostDto suburbListPostDto = SuburbTestHelper.createSuburbListPostDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/" + mockFranchiseeId.toString() + "/service_areas")
                        .content(objectMapper.writeValueAsString(suburbListPostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.suburbs[0].sscCode").value(11344L))
                .andExpect(jsonPath("$.suburbs[1].sscCode").value(12287L));

    }
}