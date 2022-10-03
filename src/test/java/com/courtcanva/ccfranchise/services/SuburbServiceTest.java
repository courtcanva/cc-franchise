package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.repositories.SuburbRepository;
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuburbServiceTest {

    @Mock
    private SuburbRepository suburbRepository;

    @InjectMocks
    private SuburbService suburbService;

    @BeforeEach
    void setUp() {
        suburbRepository.save(TestHelper.suburb1());
        suburbRepository.save(TestHelper.suburb2());
    }

    @Test
    void shouldGetSuburbListDtoWhenGetAllSuburbs(){

//        when(suburbRepository.findAll()).thenReturn(TestHelper.)
    }
}
