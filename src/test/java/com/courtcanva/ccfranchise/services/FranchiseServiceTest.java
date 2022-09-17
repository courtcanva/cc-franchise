package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.VerifyStatus;
import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.model.Franchise;
import com.courtcanva.ccfranchise.repositories.FranchiseRepository;
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
class FranchiseServiceTest {
    @Mock
    private FranchiseRepository franchiseRepository;
    @Mock
    private FranchiseeMapper franchiseeMapper;
    @InjectMocks
    private FranchiseService franchiseService;


    @Test
    void shouldReturnFranchiseIdSuccessful() {

        FranchiseDto franchiseDto = FranchiseDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .ABN(1231232)
                .build();
        Franchise franchise = Franchise.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .ABN(1231232)
                .build();
        Franchise mockReturnFranchise = Franchise.builder()
                .id(1L)
                .ABN(12321)
                .postcode(3213)
                .status(VerifyStatus.UNVERIFIED)
                .businessAddress("fdsafdsf")
                .businessName("CCCVVV")
                .build();

        when(franchiseRepository.save(any())).thenReturn(mockReturnFranchise);
        when(franchiseeMapper.toFranchiseeEntity(any())).thenReturn(franchise);
        var id = franchiseService.createFranchise(franchiseDto);
        assertEquals(1L, id);
    }

    @Test
    void shouldReturnFranchiseByABN() {
        Franchise mockReturnFranchise = Franchise.builder()
                .id(122L)
                .ABN(12321)
                .postcode(3213)
                .status(VerifyStatus.UNVERIFIED)
                .businessAddress("fdsafdsf")
                .businessName("CCCVVV")
                .build();
        when(franchiseRepository.findByABN(any())).thenReturn(Optional.ofNullable(mockReturnFranchise));
        var franchise = franchiseService.findFranchiseByABN(12321);
        assertEquals(3213, franchise.getPostcode());
    }
}