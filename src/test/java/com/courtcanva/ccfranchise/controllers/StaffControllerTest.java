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
import static org.mockito.Mockito.*;

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
    void verifyEmail() throws Exception {
        RequestBuilder request1 = MockMvcRequestBuilders.get("/staffs/email/222@gmail.com");
        RequestBuilder request2 = MockMvcRequestBuilders.get("/staffs/email/222gmail.com");
        mockMvc.perform(request1)
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(request2)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

        when(staffService.emailExists(any())).thenThrow(new ResourceAlreadyExistException("Email already existed"));
        mockMvc.perform(MockMvcRequestBuilders.get("/staffs/email/333@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
}