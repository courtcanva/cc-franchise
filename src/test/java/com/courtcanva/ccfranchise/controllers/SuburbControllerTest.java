package com.courtcanva.ccfranchise.controllers;

import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.SuburbTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SuburbControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SuburbRepository suburbRepository;

    @BeforeEach
    void setUp() {
        suburbRepository.save(SuburbTestHelper.suburb1());
        suburbRepository.save(SuburbTestHelper.suburb2());
    }

    @Test
    void shouldReturn200AndSuburbDtoWhenGetSuburbDto() throws Exception {
        mockMvc.perform(get("/suburbs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.suburbs", hasSize(2)))
                .andExpect(jsonPath("$.suburbs[0].sscCode").value(11344L));

    }
}
