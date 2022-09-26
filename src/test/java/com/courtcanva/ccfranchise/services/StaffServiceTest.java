package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    private StaffService staffService;

    @BeforeEach
    public void setStaffServiceUp() {
        StaffMapper staffMapper = new StaffMapperImpl();

        staffService = new StaffService(
                staffRepository,
                staffMapper
        );
    }

    @Test
    void shouldCreatedStaffSuccessful() {

        Staff staff = TestHelper.createStaff();

        when(staffRepository.save(any())).thenReturn(staff);

        StaffGetDto staffResult = staffService.createStaff(staff);

        assertEquals(1232L, staffResult.getStaffId());

    }

    @Test
    void checkEmailIsExisted() {
        String email = "222@gmail.com";
        when(staffRepository.existsStaffByEmail(email)).thenReturn(false);
        Assertions.assertFalse(staffService.emailExists(email));
    }
}