package com.courtcanva.ccfranchise.jwts;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.controllers.FranchiseeController;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseePostDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.dtos.suburbs.SuburbListAndFilterModePostDto;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.services.FranchiseeService;
import com.courtcanva.ccfranchise.utils.FranchiseeTestHelper;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import com.courtcanva.ccfranchise.utils.SuburbTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JwtTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FranchiseeService franchiseeService;

    @Autowired
    private FranchiseeRepository franchiseeRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private SuburbRepository suburbRepository;

    @Autowired
    private FranchiseeController franchiseeController;

    private static final String BEARER = "Bearer ";

    @BeforeEach
    public void clear() {

        staffRepository.deleteAll();

        franchiseeRepository.deleteAll();

        suburbRepository.deleteAll();

    }


    @Test
    public void ShouldReturnOKSuccessfullyWhenLogin() throws Exception {
        franchiseeService
                .createFranchiseeAndStaff(FranchiseeTestHelper.createFranchiseePostDto(), StaffTestHelper.createStaffPostDto());

        UsernameAndPasswordAuthenticationRequest user
                = new UsernameAndPasswordAuthenticationRequest();

        user.setPassword("Bfasdf1123213");
        user.setUsername("baoruoxi@163.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/staff/signin")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnForbiddenWhenUnknownFranchiseeAct() throws Exception {
        franchiseeController.signUpFranchiseeAndStaff(new FranchiseeAndStaffPostDto(new FranchiseePostDto("CourtCanva", "CourtCanva LTD", "12312123111", "23468290381", "Melbourne", AUState.VIC, 3000), new StaffPostDto("Taylor", "Swift", "taylor.s@gmail.com", "123456789", "abc st", 3000, AUState.VIC, "sdjkhsd")));
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());

        SuburbListAndFilterModePostDto suburbListAndFilterModePostDto = SuburbTestHelper.createSuburbListPostDtoWithIncludeMode();

        UsernameAndPasswordAuthenticationRequest user = new UsernameAndPasswordAuthenticationRequest();
        user.setUsername("taylor.s@gmail.com");
        user.setPassword("sdjkhsd");

        String body = objectMapper.writeValueAsString(user);

        MvcResult mockSignIn = mockMvc.perform(MockMvcRequestBuilders.post("/staff/signin")
                        .content(body))
                .andExpect(status().isOk())
                .andReturn();

        String response = mockSignIn.getResponse().getHeader("Authorization");
        assert response != null;
        String token = response.replace(BEARER, "");

        mockMvc.perform(MockMvcRequestBuilders.post("/franchisee/11/service_areas")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(suburbListAndFilterModePostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());


    }

}