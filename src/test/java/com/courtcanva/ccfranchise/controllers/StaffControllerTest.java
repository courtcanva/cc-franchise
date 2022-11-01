package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.services.StaffService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import com.courtcanva.ccfranchise.dtos.StaffVerifyEmailPostDto;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.MailingClient;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

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
    @MockBean
    private StaffService staffService;
    @InjectMocks
    private StaffController staffController;

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
    void givenValidVerificationToken_whenVerifyEmail_shouldReturnAccepted() throws Exception {
        Staff staff = StaffTestHelper.createStaffForRepository();
        StaffVerifyEmailPostDto staffVerifyEmailPostDto = StaffTestHelper.createStaffVerifyEmailPostDto(staff.getVerificationToken(), staff.getEmail());

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_VERIFICATION_URI)
                        .content(objectMapper.writeValueAsString(staffVerifyEmailPostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void givenNoneExistVerificationToken_whenVerifyEmail_shouldThrowResourceNotFoundException() throws Exception {
        Staff staff = StaffTestHelper.createStaffForRepository();
        StaffVerifyEmailPostDto staffVerifyEmailPostDto = StaffTestHelper.createStaffVerifyEmailPostDto(UUID.randomUUID().toString(), staff.getEmail());

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_VERIFICATION_URI)
                        .content(objectMapper.writeValueAsString(staffVerifyEmailPostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenExpiredVerificationToken_whenVerifyEmail_shouldThrowExpiredVerificationTokenException() throws Exception {
        Staff staff = StaffTestHelper.createStaffForRepository("tester+staff-controller-test@courtcanva.com", OffsetDateTime.now().minus(2, ChronoUnit.DAYS));
        staffRepository.save(staff);
        StaffVerifyEmailPostDto staffVerifyEmailPostDto = StaffTestHelper.createStaffVerifyEmailPostDto(staff.getVerificationToken(), staff.getEmail());

        mockMvc.perform(MockMvcRequestBuilders.post(STAFF_VERIFICATION_URI)
                        .content(objectMapper.writeValueAsString(staffVerifyEmailPostDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnSuccessWhenEmailNotExists() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/staff/emails/222@gmail.com");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenEmailFormatInvalid() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/staff/emails/222gmail.com");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturnConflictWhenEmailAlreadyExists() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/staff/emails/333@gmail.com");
        when(staffService.emailExists(any())).thenThrow(new ResourceAlreadyExistException("Email already existed"));
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
}
