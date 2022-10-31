package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.services.StaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
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

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class StaffControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StaffService staffService;
    @InjectMocks
    private StaffController staffController;

    @Test
    void shouldReturnSuccessWhenEmailNotExists() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/staffs/emails/222@gmail.com");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenEmailFormatInvalid() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/staffs/emails/222gmail.com");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturnConflictWhenEmailAlreadyExists() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/staffs/emails/333@gmail.com");
        when(staffService.emailExists(any())).thenThrow(new ResourceAlreadyExistException("Email already existed"));
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
}