package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.VerifyStatus;
import com.courtcanva.ccfranchise.dtos.FranchiseDto;
import com.courtcanva.ccfranchise.dtos.StaffDto;
import com.courtcanva.ccfranchise.model.Franchise;
import com.courtcanva.ccfranchise.model.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import com.courtcanva.ccfranchise.utility.mappers.StaffMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock
    private FranchiseService franchiseService;

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private StaffMapper staffMapper;

    @InjectMocks
    private StaffService staffService;

    @Test
    void shouldReturnStaffIdSuccessful(){
        FranchiseDto franchiseDto = FranchiseDto.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .ABN(1231232)
                .build();
        StaffDto staffDto = StaffDto.builder()
                .address("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state("NSW")
                .postcode(3213)
                .password("fdsafsdfdsaf")
                .phoneNumber("31232131")
                .firstName("first")
                .lastName("last")
                .build();
        franchiseDto.setStaff(staffDto);
        Franchise mockFranchise = Franchise.builder()
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .ABN(1231232)
                .build();
        Staff mockStaff = Staff.builder()
                .id(1232L)
                .status(VerifyStatus.UNVERIFIED)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .address("fsdfsdafa")
                .build();

        when(staffMapper.toStaffEntity(any())).thenReturn(mockStaff);
        when(franchiseService.findFranchiseByABN(any()))
                .thenReturn(mockFranchise);
        when(staffRepository.save(any())).thenReturn(mockStaff);

        var staffId = staffService.createStaffOfFranchise(franchiseDto);

        assertEquals(1232L,staffId);



    }
}