package com.courtcanva.ccfranchise.jwts;

import com.courtcanva.ccfranchise.services.FranchiseeService;
import com.courtcanva.ccfranchise.utils.TestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
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



    @Test
    public void ShouldReturnOKSuccessfullyWhenLogin() throws Exception {
        franchiseeService
                .createFranchiseeAndStaff(TestHelper.createFranchiseePostDto(),TestHelper.createStaffPostDto());

        UsernameAndPasswordAuthenticationRequest user
                = new UsernameAndPasswordAuthenticationRequest();

        user.setPassword("Bfasdf1123213");
        user.setUsername("baoruoxi@163.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/staff/login")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

}