package com.courtcanva.ccfranchise.controllers;

import com.amazonaws.util.Base64;
import com.courtcanva.ccfranchise.exceptions.MailingClientException;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.MailingClient;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class StaffControllerTest {
    private static final String STAFF_REQUEST_VERIFICATION_URI = "/staff/verification";
    private static final String STAFF_VERIFICATION_URI = "/staff/verify";

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
        staffRepository.save(StaffTestHelper.createStaffForRepository());
    }

    @Test
    void givenValidStaffId_whenSendVerificationToken_shouldReturnCreated() throws Exception {
        Staff staff = staffRepository.findByEmail(StaffTestHelper.createStaffForRepository().getEmail()).orElseThrow();

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_REQUEST_VERIFICATION_URI)
                        .param("id", String.valueOf(staff.getId()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void givenInvalidMailingClientConfig_whenSendVerificationEmail_shouldThrowMailingClientException() throws Exception {
        doThrow(new MailingClientException("Invalid Mailing Client Configuration")).when(mailingClient).sendEmail(any(), any(), any(), any());

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_REQUEST_VERIFICATION_URI)
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void givenValidVerificationToken_whenVerifyEmail_shouldReturnAccepted() throws Exception {
        Staff staff = StaffTestHelper.createStaffForRepository();

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_VERIFICATION_URI)
                        .param("token", staff.getVerificationToken())
                        .param("i", Base64.encodeAsString(staff.getEmail().getBytes()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void givenNoneExistVerificationToken_whenVerifyEmail_shouldThrowNoSuchElementException() throws Exception {
        Staff staff = StaffTestHelper.createStaffForRepository();

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_VERIFICATION_URI)
                        .param("token", UUID.randomUUID().toString())
                        .param("i", Base64.encodeAsString(staff.getEmail().getBytes()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenExpiredVerificationToken_whenVerifyEmail_shouldThrowExpiredVerificationTokenException() throws Exception {
        Staff staff = StaffTestHelper.createStaffForRepository("tester+staff-controller-test@courtcanva.com", OffsetDateTime.now().minus(2, ChronoUnit.DAYS));
        staffRepository.save(staff);

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_VERIFICATION_URI)
                        .param("token", staff.getVerificationToken())
                        .param("i", Base64.encodeAsString(staff.getEmail().getBytes()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }


}
