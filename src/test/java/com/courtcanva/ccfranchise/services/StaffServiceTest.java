package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.exceptions.ResourceAlreadyExistException;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utils.TestHelper;
import org.junit.jupiter.api.Assertions;
import com.courtcanva.ccfranchise.utils.StaffTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        Staff staff = StaffTestHelper.createStaff();

        when(staffRepository.save(any())).thenReturn(staff);

        StaffGetDto staffResult = staffService.createStaff(staff);

        assertEquals(1232L, staffResult.getStaffId());

    }

    @Test
    void shouldThrowErrorWhenEmailExits() {
        String email1 = "222@gmail.com";
        when(staffRepository.existsStaffByEmail(email1)).thenReturn(false);
        Assertions.assertFalse(staffService.emailExists(email1));

        String email2 = "333@gmail.com";
        when(staffRepository.existsStaffByEmail(email2)).thenReturn((true));
        assertThrows(ResourceAlreadyExistException.class,
                () -> staffService.emailExists(email2));
    }
}