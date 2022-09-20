package com.courtcanva.ccfranchise.services;

import com.courtcanva.ccfranchise.constants.AUState;
import com.courtcanva.ccfranchise.dtos.StaffGetDto;
import com.courtcanva.ccfranchise.dtos.StaffPostDto;
import com.courtcanva.ccfranchise.mappers.StaffMapper;
import com.courtcanva.ccfranchise.models.Franchisee;
import com.courtcanva.ccfranchise.models.Staff;
import com.courtcanva.ccfranchise.repositories.StaffRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private StaffMapper staffMapper;

    @InjectMocks
    private StaffService staffService;

    @Test
    void shouldCreatedStaffSuccessful() {

        StaffPostDto staffPostDto = StaffPostDto.builder()
                .residentialAddress("gadsfadsfdsa")
                .email("baoruoxi@163.com")
                .state(AUState.ACT)
                .postcode(3213)
                .password("Bfasd1999324")
                .phoneNumber("31232131")
                .firstName("first")
                .lastName("last")
                .build();

        Franchisee mockFranchisee = Franchisee.builder()
                .id(1234L)
                .businessName("AAAAA")
                .businessAddress("zetland NSWssss")
                .abn("1231232")
                .build();
        StaffGetDto mockStaffGetDto = StaffGetDto.builder()
                .staffId(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();

        Staff mockStaff = Staff.builder()
                .id(1232L)
                .isVerified(false)
                .firstName("ll")
                .lastName("ff")
                .email("666@gmail.com")
                .residentialAddress("fsdfsdafa")
                .build();

        when(staffMapper.staffPostDtoToStaff(any())).thenReturn(mockStaff);
        when(staffMapper.staffToStaffGetDto(any())).thenReturn(mockStaffGetDto);

        StaffGetDto staff = staffService.createStaffWithFranchisee(staffPostDto, mockFranchisee);

        assertEquals(1232L, staff.getStaffId());

    }
}