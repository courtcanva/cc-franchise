package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.VerifyStatus;
import com.courtcanva.ccfranchise.dtos.FranchiseeDto;
import com.courtcanva.ccfranchise.model.Franchisee;
import com.courtcanva.ccfranchise.repositories.FranchiseeRepository;
import com.courtcanva.ccfranchise.utility.mappers.FranchiseeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("test")
class FranchiseeServiceTest {
    @Mock
    private FranchiseeRepository franchiseeRepository;
    @Mock
    private FranchiseeMapper franchiseeMapper;
    @InjectMocks
    private FranchiseeService franchiseeService;


    @Test
    void shouldReturnFranchiseIdSuccessful() {

        FranchiseeDto franchiseeDto = FranchiseeDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .ABN(1231232)
                .build();
        Franchisee franchisee = Franchisee.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .ABN(1231232)
                .build();
        Franchisee mockReturnFranchisee = Franchisee.builder()
                .id(1L)
                .ABN(12321)
                .postcode(3213)
                .status(VerifyStatus.UNVERIFIED)
                .businessAddress("fdsafdsf")
                .businessName("CCCVVV")
                .build();

        when(franchiseeRepository.save(any())).thenReturn(mockReturnFranchisee);
        when(franchiseeMapper.toFranchiseeEntity(any())).thenReturn(franchisee);
        var id = franchiseeService.createFranchisee(franchiseeDto);
        assertEquals(1L, id);
    }

    @Test
    void shouldReturnFranchiseByABN() {
        Franchisee mockReturnFranchisee = Franchisee.builder()
                .id(122L)
                .ABN(12321)
                .postcode(3213)
                .status(VerifyStatus.UNVERIFIED)
                .businessAddress("fdsafdsf")
                .businessName("CCCVVV")
                .build();
        when(franchiseeRepository.findByABN(any())).thenReturn(Optional.ofNullable(mockReturnFranchisee));
        var franchisee = franchiseeService.findFranchiseeByABN(12321);
        assertEquals(3213, franchisee.getPostcode());
    }
}