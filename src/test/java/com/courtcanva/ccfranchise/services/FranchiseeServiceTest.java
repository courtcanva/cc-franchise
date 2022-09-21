package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffGetDto;
import com.courtcanva.ccfranchise.exceptions.ResourceNotFoundException;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapperImpl;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class FranchiseeServiceTest extends ServiceHelper {

    @Mock
    private FranchiseeRepository franchiseeRepository;

    @Mock
    private StaffService staffService;

    private FranchiseeService franchiseeService;

    @BeforeEach
    public void setFranchiseeServiceUp() {
        super.setUp();
        FranchiseeMapper franchiseeMapper = new FranchiseeMapperImpl();

        franchiseeService = new FranchiseeService(
                franchiseeRepository, franchiseeMapper, staffService
        );
    }

    @Test
    void shouldCreateStaffAndFranchiseeGetDto() {

        when(franchiseeRepository.save(any())).thenReturn(mockFranchisee);
        when(staffService.createStaffWithFranchisee(any(), any())).thenReturn(mockStaffGetDto);

        FranchiseeAndStaffGetDto franchiseeAndStaffGetDto = franchiseeService.createFranchiseeAndStaff(mockFranchiseeAndStaffPostDto);
        assertEquals(1234L, franchiseeAndStaffGetDto.getFranchiseeGetDto().getFranchiseeId());
    }

    @Test
    void shouldReturnFranchiseeGetDto() {

        Long franchiseeId = 1234L;
        when(franchiseeRepository.findById(franchiseeId)).thenReturn(Optional.ofNullable(mockFranchisee));

        Franchisee franchisee = franchiseeService.getFranchiseeById(franchiseeId);

        assertEquals("1231232", franchisee.getAbn());
    }

    @Test
    void shouldThrowResourceNotFoundException() {

        Long franchiseeId = 124L;

        when(franchiseeRepository.findById(franchiseeId)).thenThrow(new ResourceNotFoundException("franchisee not found"));

        assertThrows(ResourceNotFoundException.class, () -> franchiseeService.getFranchiseeById(franchiseeId));
    }

}