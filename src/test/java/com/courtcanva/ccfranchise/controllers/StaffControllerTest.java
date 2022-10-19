package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.dtos.StaffVerifyEmailPostDto;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.MailingClient;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StaffControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StaffRepository staffRepository;

    @MockBean
    private MailingClient mailingClient;

    @BeforeEach
    public void setupTest() {
        staffRepository.deleteAll();
        staffRepository.save(StaffTestHelper.createStaff());
    }

    @Test
    void whenSendVerificationToken_shouldReturnCreated() throws Exception {
        doNothing().when(mailingClient).sendEmail(any(), any(), any(), any());

        mockMvc.perform(MockMvcRequestBuilders.post("/staff/send-verification-email")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    @Disabled
    void whenVerifyEmail_shouldReturnAccepted() throws Exception {
        StaffVerifyEmailPostDto staffVerifyEmailPostDto = StaffTestHelper.createStaffVerifyEmailPostDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/staff/verify-email")
                        .content(objectMapper.writeValueAsString(staffVerifyEmailPostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
