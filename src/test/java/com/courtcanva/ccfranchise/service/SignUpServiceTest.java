package com.courtcanva.ccfranchise.service;

import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.services.SignUpService;
import com.courtcanva.ccfranchise.services.StaffService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {
    @Mock
    private StaffRepository staffRepository;

    private SignUpService signUpService;

    @BeforeEach
    public void setStaffServiceUp() {
        signUpService = new SignUpService(
                staffRepository
        );
    }

    @Test
    void checkEmailIsExisted() {
        String email = "222@gmail.com";
        when(staffRepository.existsStaffByEmail(email)).thenReturn(false);
        Assertions.assertFalse(signUpService.checkEmailIsExisted(email));
    }
}