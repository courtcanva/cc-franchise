package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.mappers.StaffMapperImpl;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StaffServiceTest extends ServiceHelper {

    @Mock
    private StaffRepository staffRepository;

    private StaffService staffService;

    @BeforeEach
    public void setStaffServiceUp() {
        super.setUp();
        StaffMapper staffMapper = new StaffMapperImpl();

        staffService = new StaffService(
                staffRepository,
                staffMapper
        );
    }

    @Test
    void shouldCreatedStaffSuccessful() {


        when(staffRepository.save(any())).thenReturn(mockStaff);

        StaffGetDto staff = staffService.createStaffWithFranchisee(mockStaffPostDto, mockFranchisee);

        assertEquals(1232L, staff.getStaffId());

    }
}