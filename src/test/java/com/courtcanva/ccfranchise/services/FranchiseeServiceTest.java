package com.courtcanva.ccfranchise.services;


import com.courtcanva.ccfranchise.dtos.FranchiseeAndStaffInfoDto;
import com.courtcanva.ccfranchise.dtos.ResponseDto;
import com.courtcanva.ccfranchise.mappers.FranchiseeMapper;
import com.courtcanva.ccfranchise.model.Franchisee;
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

        FranchiseeAndStaffInfoDto franchiseeDto = FranchiseeAndStaffInfoDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("1231232")
                .build();
        Franchisee franchisee = Franchisee.builder()
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

        when(franchiseeRepository.save(any())).thenReturn(mockReturnFranchisee);
        when(franchiseeMapper.toFranchiseeEntity(any())).thenReturn(franchisee);


        ResponseDto responseDto = franchiseeService.createFranchiseeAndStaff(franchiseeDto);
        assertEquals("201", responseDto.getResponseCode());
    }

    @Test
    void shouldReturnFranchiseByABN() {
        Franchisee mockReturnFranchisee = Franchisee.builder()
                .id(122L)
                .abn("12321")
                .postcode(3213)
                .isVerified(false)
                .businessAddress("fdsafdsf")
                .businessName("CCCVVV")
                .build();
        when(franchiseeRepository.findByAbn(any())).thenReturn(Optional.ofNullable(mockReturnFranchisee));

        Franchisee franchisee = franchiseeService.findFranchiseeByABN("12321");

        assertEquals(3213, franchisee.getPostcode());
    }
}