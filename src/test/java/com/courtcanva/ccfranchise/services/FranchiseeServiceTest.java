package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffGetDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffPostDto;
import com.courtcanva.ccfranchise.dtos.FranchiseeGetDto;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FranchiseeServiceTest {
    @Mock
    private FranchiseeRepository franchiseeRepository;
    @Mock
    private FranchiseeMapper franchiseeMapper;
    @Mock
    private StaffService staffService;
    @InjectMocks
    private FranchiseeService franchiseeService;


    @Test
    void shouldCreateStaffAndFranchiseeSuccessful() {

        FranchiseeAndStaffPostDto franchiseeDto = FranchiseeAndStaffPostDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("1231232")
                .build();
        Franchisee mockReturnFranchisee = Franchisee.builder()
                .id(1L)
                .abn("1231232")
                .postcode(3213)
                .isVerified(false)
                .businessAddress("fdsafdsf")
                .businessName("CCCVVV")
                .build();
        StaffGetDto mockStaffGetDto = StaffGetDto.builder()
                .staffId(5544L)
                .build();
        FranchiseeGetDto mockFranchiseeGetDto  = FranchiseeGetDto.builder()
                .franchiseeId(666L)
                .build();

        when(franchiseeRepository.save(any())).thenReturn(mockReturnFranchisee);
        when(franchiseeMapper.franchiseeToFranchiseeGetDto(any())).thenReturn(mockFranchiseeGetDto);
        when(staffService.createStaffWithFranchisee(any(),any())).thenReturn(mockStaffGetDto);

        FranchiseeAndStaffGetDto franchiseeAndStaffGetDto = franchiseeService.createFranchiseeAndStaff(franchiseeDto);
        assertEquals(666L, franchiseeAndStaffGetDto.getFranchiseeGetDto().getFranchiseeId());
    }

    @Test
    void shouldReturnFranchiseById() {
        Franchisee mockReturnFranchisee = Franchisee.builder()
                .id(122L)
                .abn("12321")
                .postcode(3213)
                .isVerified(false)
                .businessAddress("fdsafdsf")
                .businessName("CCCVVV")
                .build();
        when(franchiseeRepository.findById(any())).thenReturn(Optional.ofNullable(mockReturnFranchisee));

        Franchisee franchisee = franchiseeService.findFranchiseeById(122L);

        assertEquals(3213, franchisee.getPostcode());
    }
}